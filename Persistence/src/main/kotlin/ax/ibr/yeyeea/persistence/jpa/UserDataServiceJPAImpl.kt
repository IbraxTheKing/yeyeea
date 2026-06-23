package ax.ibr.yeyeea.persistence.jpa

import ax.ibr.utils.services.jpa.CrudJpaService
import ax.ibr.yeyeea.persistence.dataservice.UserDataService
import ax.ibr.yeyeea.common.entities.User
import jakarta.persistence.EntityManager

class UserDataServiceJPAImpl(pu: String, em: EntityManager,
                             entityClass: Class<User>
) : UserDataService, CrudJpaService<User>(em, entityClass) {


    override fun getByUsername(username: String?): List<User> {
        return em.createQuery(
            "SELECT u FROM User u WHERE u.username = :username",
            User::class.java
        ).setParameter("username", username)
            .resultList
    }

    override fun getByEmail(email: String): List<User> {
        return em.createQuery(
            "SELECT u FROM User u WHERE u.email = :email",
            User::class.java
        ).setParameter("email", email)
            .resultList
    }

}
