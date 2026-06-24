package ax.ibr.yeyeea.restserver.filters

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