package ax.ibr.utils.rest

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequiresAuth(
    val roles: Array<String> = [],
    val allowOwner: Boolean = false,
    val ownerParam: String = "id"
)