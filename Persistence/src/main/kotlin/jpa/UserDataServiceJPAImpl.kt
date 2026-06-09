package jpa

import dataservice.UserDataService
import entities.User
import jakarta.persistence.EntityManager

class UserDataServiceJPAImpl(pu: String, em: EntityManager,
                             entityClass: Class<User>
) : UserDataService, JpaDao<User>(em, entityClass) {


    override fun getByUsername(username: String): List<User> {
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
