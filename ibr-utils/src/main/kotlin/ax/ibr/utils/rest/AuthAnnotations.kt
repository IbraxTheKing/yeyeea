package ax.ibr.utils.rest

import kotlin.reflect.KClass

/**
 * Marks a resource method as requiring an authenticated user.
 *
 * @param roles Allowed roles. If empty, any authenticated user is allowed.
 * @param allowOwner Whether to also allow access to the owner of the targeted
 *                   resource. Ownership is determined by the resolver
 *                   specified with [OwnerResolvedBy].
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequiresAuth(
    val roles: Array<String> = [],
    val allowOwner: Boolean = false
)

/**
 * Shortcut for `@RequiresAuth(roles = [value])`.
 *
 * @param value The required role.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequiresRole(
    val value: String
)

/**
 * Determines whether the authenticated user owns the targeted resource.
 *
 * Each application is responsible for implementing its own ownership logic.
 * For example:
 * - Comparing the authenticated user's ID with the resource owner's ID.
 * - Checking whether the authenticated user owns a product, order, etc.
 */
fun interface OwnerResolver {

    /**
     * Returns `true` if the authenticated user owns the resource identified
     * by the given path ID.
     *
     * @param pathId The resource identifier extracted from the request path.
     * @param authUserId The authenticated user's identifier.
     * @return `true` if the authenticated user owns the resource, otherwise `false`.
     */
    fun isOwner(pathId: Long, authUserId: Long): Boolean
}

/**
 * Specifies which [OwnerResolver] should be used for a resource method.
 *
 * This annotation is typically used together with
 * `@RequiresAuth(allowOwner = true)`. If no resolver is specified,
 * owner-based authorization is ignored.
 *
 * The resolver implementation may either be:
 * - a Kotlin `object` singleton, or
 * - a class with a public no-argument constructor.
 *
 * @param resolver The [OwnerResolver] implementation to use.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class OwnerResolvedBy(val resolver: KClass<out OwnerResolver>)