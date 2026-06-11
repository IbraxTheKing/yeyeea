package ax.ibr.yeyeea.persistence.dataservice

import ax.ibr.yeyeea.common.entities.Category
import ax.ibr.yeyeea.common.entities.Product
import ax.ibr.yeyeea.common.entities.User
import ax.ibr.yeyeea.persistence.jdbc.CategoryDataServiceJDBCImpl
import ax.ibr.yeyeea.persistence.jdbc.ProductDataServiceJDBCImpl
import ax.ibr.yeyeea.persistence.jdbc.UserDataServiceJDBCImpl
import ax.ibr.yeyeea.persistence.jpa.CategoryDataServiceJPAImpl
import ax.ibr.yeyeea.persistence.jpa.UserDataServiceJPAImpl
import ax.ibr.yeyeea.common.services.CategoryService
import ax.ibr.yeyeea.common.services.ProductService
import ax.ibr.yeyeea.common.services.UserService
import ax.ibr.yeyeea.persistence.jpa.ProductDataServiceJPAImpl
import jakarta.persistence.EntityManager
import jakarta.persistence.Persistence
import java.sql.Connection
import java.sql.DriverManager
import kotlin.getValue

class PersistenceFactory {
    private lateinit var userService: UserService
    private lateinit var categoryService: CategoryService
    private lateinit var productService: ProductService

    private val JDBC: Boolean = false
    private val PU: String = "my-persistence-unit"

    private val connection: Connection by lazy {
        DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/mydb",
            "user",
            "password"
        )
    }

    private val entityManager: EntityManager by lazy {
        Persistence.createEntityManagerFactory(PU).createEntityManager()
    }

    fun getUserDataService(): UserService {
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

    fun getCategoryDataService(): CategoryService {
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

    fun getProductDataService(): ProductService {
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
}