package ax.ibr.utils.rest

import ax.ibr.utils.security.JwtService
import jakarta.annotation.Priority
import jakarta.ws.rs.Priorities
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.container.DynamicFeature
import jakarta.ws.rs.container.ResourceInfo
import jakarta.ws.rs.core.FeatureContext
import jakarta.ws.rs.core.HttpHeaders
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.Provider

/**
 * Inspecte chaque méthode de ressource au démarrage et, si elle porte
 * @RequiresAuth ou @RequiresRole, enregistre le filtre qui appliquera
 * la vérification à chaque requête. Générique : ne dépend d'aucune
 * classe applicative, seulement des annotations de ce package.
 */
@Provider
class AuthDynamicFeature : DynamicFeature {

    override fun configure(resourceInfo: ResourceInfo, context: FeatureContext) {

        val method = resourceInfo.resourceMethod

        val requiresAuth = method.getAnnotation(RequiresAuth::class.java)
        val requiresRole = method.getAnnotation(RequiresRole::class.java)

        val roles: List<String>
        val allowOwner: Boolean

        when {
            requiresAuth != null -> {
                roles = requiresAuth.roles.toList()
                allowOwner = requiresAuth.allowOwner
            }
            requiresRole != null -> {
                roles = listOf(requiresRole.value)
                allowOwner = false
            }
            else -> return // pas d'annotation -> endpoint public
        }

        val ownerResolver: OwnerResolver? = if (!allowOwner) null else {
            val declared = method.getAnnotation(OwnerResolvedBy::class.java)
                ?: throw IllegalStateException(
                    "${resourceInfo.resourceClass.simpleName}.${method.name} déclare " +
                        "allowOwner = true sans @OwnerResolvedBy(...)"
                )

            declared.resolver.objectInstance
                ?: declared.resolver.java.getDeclaredConstructor().newInstance()
        }

        context.register(AuthCheckFilter(roles, ownerResolver))
    }
}

@Priority(Priorities.AUTHENTICATION)
class AuthCheckFilter(
    private val roles: List<String>,
    private val ownerResolver: OwnerResolver?
) : ContainerRequestFilter {

    override fun filter(requestContext: ContainerRequestContext) {

        val header = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION)

        if (header == null || !header.startsWith("Bearer ")) {
            requestContext.abortWith(unauthorized("Token manquant"))
            return
        }

        val claims = JwtService.parseToken(header.removePrefix("Bearer ").trim())
            ?: run {
                requestContext.abortWith(unauthorized("Token invalide ou expiré"))
                return
            }

        val authUserId = JwtService.getUserId(claims)
        val authRole = JwtService.getRole(claims)

        val hasRole = roles.isEmpty() || roles.contains(authRole)

        val isOwner = ownerResolver != null && run {
            val pathId = requestContext.uriInfo.pathParameters.getFirst("id")?.toLongOrNull()
            pathId != null && ownerResolver?.isOwner(pathId, authUserId) == true
        }

        if (!hasRole && !isOwner) {
            requestContext.abortWith(
                Response.status(Response.Status.FORBIDDEN)
                    .entity(mapOf("error" to "Accès refusé"))
                    .build()
            )
            return
        }

        requestContext.setProperty("authUserId", authUserId)
        requestContext.setProperty("authUserRole", authRole)
    }

    private fun unauthorized(message: String): Response =
        Response.status(Response.Status.UNAUTHORIZED)
            .entity(mapOf("error" to message))
            .build()
}
