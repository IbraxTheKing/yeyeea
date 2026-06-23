package ax.ibr.yeyeea.restserver.filters

import ax.ibr.yeyeea.restserver.RequiresRole
import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.Provider

/*

@Provider
class SecurityFilter : ContainerRequestFilter {

    override fun filter(requestContext: ContainerRequestContext) {
        val role = getUserRoleFromToken()

        val resourceMethod = requestContext.uriInfo
            .matchedResources[0]
            .javaClass
            .methods
            .find { it.isAnnotationPresent(RequiresRole::class.java) }

        val annotation = resourceMethod?.getAnnotation(RequiresRole::class.java)

        if (annotation != null && role != annotation.value) {
            requestContext.abortWith(
                Response.status(403).build()
            )
        }
    }

    fun getUserRoleFromToken() {

    }
} */