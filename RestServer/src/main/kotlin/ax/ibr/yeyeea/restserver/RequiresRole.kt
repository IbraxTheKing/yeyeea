package ax.ibr.yeyeea.restserver

import ax.ibr.yeyeea.common.entities.UserType

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequiresRole(val value: UserType)
