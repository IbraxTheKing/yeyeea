package ax.ibr.yeyeea.business.implementations

import ax.ibr.utils.exceptions.AlreadyExistsException
import ax.ibr.yeyeea.persistence.dataservice.PersistenceFactory
import ax.ibr.yeyeea.common.entities.User
import ax.ibr.yeyeea.common.services.UserService
import kotlin.jvm.Throws

class UserServiceImpl : UserService {

    private val userService: UserService = PersistenceFactory().getUserDataService()

    override fun getByUsername(username: String?): List<User> {
        return userService.getByUsername(username)
    }

    override fun getByEmail(email: String): List<User> {
        return userService.getByEmail(email)
    }

    override fun add(t: User) {
        val existingUser = userService.getByUsername(t.username).firstOrNull()

        if (existingUser != null) {
            throw AlreadyExistsException("User ${t.username} already exists")
        }

        userService.add(t)
    }

    override fun update(t: User) {
        userService.update(t)
    }

    override fun remove(t: User) {
        userService.remove(t)
    }

    override fun getAll(): List<User> {
        return userService.getAll()
    }

    override fun getById(id: Long): User? {
        return userService.getById(id)
    }

}