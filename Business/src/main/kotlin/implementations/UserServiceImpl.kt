package implementations

import entities.User
import services.UserService

class UserServiceImpl : UserService {

    var userService: UserService = PersistenceFactory.getUserDataService()

    override fun getByUsername(username: String): List<User> {
        return userService.getByUsername(username)
    }

    override fun getByEmail(email: String): List<User> {
        return userService.getByEmail(email)
    }

    override fun add(t: User) {
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

    override fun getById(id: String): User? {
        return userService.getById(id)
    }

}