package ax.ibr.yeyeea.common.services

import ax.ibr.utils.services.CrudService
import ax.ibr.yeyeea.common.entities.Cart
import ax.ibr.yeyeea.common.entities.User

interface CartService : CrudService<Cart> {
    fun getByUser(user: User): Cart?
    fun getByUserId(userId: Long): Cart?
}