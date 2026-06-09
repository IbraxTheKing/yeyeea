package ax.ibr.yeyeea.common.services

import ax.ibr.yeyeea.common.entities.User

interface UserService : CrudService<User> {
    fun getByUsername(username: String): List<User>
    fun getByEmail(email: String): List<User>
}