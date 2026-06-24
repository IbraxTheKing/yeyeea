package ax.ibr.yeyeea.business.implementations

import ax.ibr.yeyeea.common.entities.Cart
import ax.ibr.yeyeea.common.entities.User
import ax.ibr.yeyeea.common.services.CartService
import ax.ibr.yeyeea.persistence.dataservice.PersistenceFactory

class CartServiceImpl : CartService {

    private val cartService: CartService = PersistenceFactory().getCartDataService()

    override fun getByUser(user: User): Cart? {
        return cartService.getByUser(user)
    }

    override fun getByUserId(userId: Long): Cart? {
        return cartService.getByUserId(userId)
    }

    override fun add(t: Cart) {
        cartService.add(t)
    }

    override fun update(t: Cart) {
        cartService.update(t)
    }

    override fun remove(t: Cart) {
        cartService.remove(t)
    }

    override fun getAll(): List<Cart> {
        return cartService.getAll()
    }

    override fun getById(id: Long): Cart? {
        return cartService.getById(id)
    }
}