package ax.ibr.yeyeea.persistence.dataservice

import ax.ibr.yeyeea.common.entities.Cart
import ax.ibr.yeyeea.common.entities.Category
import ax.ibr.yeyeea.common.entities.Product
import ax.ibr.yeyeea.common.entities.User
import ax.ibr.yeyeea.persistence.jdbc.CartDataServiceJDBCImpl
import ax.ibr.yeyeea.persistence.jdbc.CategoryDataServiceJDBCImpl
import ax.ibr.yeyeea.persistence.jdbc.ProductDataServiceJDBCImpl
import ax.ibr.yeyeea.persistence.jdbc.UserDataServiceJDBCImpl
import ax.ibr.yeyeea.persistence.jpa.CartDataServiceJPAImpl
import ax.ibr.yeyeea.persistence.jpa.CategoryDataServiceJPAImpl
import ax.ibr.yeyeea.persistence.jpa.ProductDataServiceJPAImpl
import ax.ibr.yeyeea.persistence.jpa.UserDataServiceJPAImpl
import jakarta.persistence.EntityManager
import jakarta.persistence.Persistence
import java.sql.Connection
import java.sql.DriverManager

class PersistenceFactory {
    private lateinit var userService: UserDataService
    private lateinit var categoryService: CategoryDataService
    private lateinit var productService: ProductDataService

    private lateinit var cartService: CartDataService

    private val JDBC: Boolean = false
    private val PU: String = "yeyaPU"

    private val connection: Connection by lazy {
        DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/yeya",
            "yeya",
            "1010"
        )
    }

    /* TODO: READ USER AND USER OF DB BY .ENV FILE */

    private val entityManager: EntityManager by lazy {
        Persistence.createEntityManagerFactory(PU).createEntityManager()
    }

    fun getUserDataService(): UserDataService {
        if (!::userService.isInitialized) {
            userService = if (JDBC) {
                UserDataServiceJDBCImpl(
                    connection = connection
                )
            } else {
                UserDataServiceJPAImpl(
                    PU,
                    em = entityManager,
                    entityClass = User::class.java
                )
            }
        }
        return userService
    }

    fun getCategoryDataService(): CategoryDataService {
        if (!::categoryService.isInitialized) {
            categoryService = if (JDBC) {
                CategoryDataServiceJDBCImpl(
                    connection = connection
                )
            } else {
                CategoryDataServiceJPAImpl(
                    em = entityManager,
                    entityClass = Category::class.java
                )
            }
        }
        return categoryService
    }

    fun getProductDataService(): ProductDataService {
        if (!::productService.isInitialized) {
            productService = if (JDBC) {
                ProductDataServiceJDBCImpl(
                    connection = connection,
                    categoryService = getCategoryDataService() as CategoryDataServiceJDBCImpl
                )
            } else {
                ProductDataServiceJPAImpl(
                    em = entityManager,
                    entityClass = Product::class.java
                )
            }
        }
        return productService
    }

    fun getCartDataService(): CartDataService {
        if (!::cartService.isInitialized) {
            cartService = if (JDBC) {
                CartDataServiceJDBCImpl(
                    connection = connection
                )
            } else {
                CartDataServiceJPAImpl(
                    em = entityManager,
                    entityClass = Cart::class.java
                )
            }
        }
        return cartService
    }
}