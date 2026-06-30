package ax.ibr.yeyeea.persistence.jpa

import ax.ibr.utils.services.jpa.CrudJpaService
import ax.ibr.yeyeea.common.entities.Cart
import ax.ibr.yeyeea.common.entities.User
import ax.ibr.yeyeea.persistence.dataservice.CartDataService
import jakarta.persistence.EntityManager

class CartDataServiceJPAImpl(
    em: EntityManager,
    entityClass: Class<Cart>
) : CartDataService, CrudJpaService<Cart>(em, entityClass) {

    override fun getByUser(user: User): Cart? {
        return em.createQuery(
            "SELECT c FROM Cart c WHERE c.owner = :user",
            Cart::class.java
        )
            .setParameter("user", user)
            .resultList
            .firstOrNull()
    }

    override fun getByUserId(userId: Long): Cart? {
        return em.createQuery(
            "SELECT c FROM Cart c WHERE c.owner.id = :userId",
            Cart::class.java
        )
            .setParameter("userId", userId)
            .resultList
            .firstOrNull()
    }
}