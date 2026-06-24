package ax.ibr.yeyeea.business.implementations

import ax.ibr.utils.exceptions.AlreadyExistsException
import ax.ibr.yeyeea.common.entities.Cart
import ax.ibr.yeyeea.common.entities.User
import ax.ibr.yeyeea.common.services.CartService
import ax.ibr.yeyeea.common.services.UserService
import ax.ibr.yeyeea.persistence.dataservice.PersistenceFactory
import jakarta.ws.rs.NotFoundException

class UserServiceImpl : UserService {

    private val userService: UserService = PersistenceFactory().getUserDataService()
    private val cartService: CartService = BusinessFactory().getCartService()

    override fun getByUsername(username: String?): List<User> {
        return userService.getByUsername(username)
    }

    override fun getByEmail(email: String): List<User> {
        return userService.getByEmail(email)
    }

    override fun add(t: User) {
        val existingUser = userService.getByUsername(t.username).firstOrNull()

        if (existingUser != null) {
            throw AlreadyExistsException("User '${t.username}' already exists")
        }

        userService.add(t)
        cartService.add(Cart(t))
    }

    override fun update(t: User) {
        val existingUser = userService.getById(t.id!!)
            ?: throw NotFoundException("User ${t.id} not found")

        val userWithSameUsername = userService.getAll()
            .find { it.username == t.username && it.id != t.id }

        if (userWithSameUsername != null) {
            throw AlreadyExistsException("Username '${t.username}' already exists")
        }

        userService.update(t)
    }

    override fun remove(t: User) {
        cartService.remove(cartService.getByUser(t)!!)
        userService.remove(t)
    }

    override fun getAll(): List<User> {
        return userService.getAll()
    }

    override fun getById(id: Long): User? {
        return userService.getById(id)
    }


}