package ax.ibr.yeyeea.common.services

import ax.ibr.utils.services.CrudService
import ax.ibr.yeyeea.common.entities.User

/**
 * @author ib <pro.ibr.ben@gmail.com>
 */

interface UserService : CrudService<User> {
    fun getByUsername(username: String): List<User>
    fun getByEmail(email: String): List<User>
}