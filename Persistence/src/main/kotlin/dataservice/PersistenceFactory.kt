package dataservice

import jdbc.CategoryDataServiceJDBCImpl
import jdbc.ProductDataServiceJDBCImpl
import jdbc.UserDataServiceJDBCImpl
import jpa.CategoryDataServiceJPAImpl
import jpa.ProductDataServiceJPAImpl
import jpa.UserDataServiceJPAImpl
import services.CategoryService
import services.ProductService
import services.UserService

class PersistenceFactory {
    private lateinit var userService: UserService
    private lateinit var categoryService: CategoryService
    private lateinit var productService: ProductService

    private var JDBC: Boolean = false
    private val PU: String = ""

    fun getUserDataService() : UserService {
        if (!::userService.isInitialized) {
            if (this.JDBC) {
                userService = UserDataServiceJDBCImpl(
                    connection = TODO()
                )
            }
            else {
                userService = UserDataServiceJPAImpl(
                    PU,
                    em = TODO(),
                    entityClass = TODO()
                )
            }
        }
        return userService
    }

    fun getCategoryDataService() : CategoryService {
        if (!::categoryService.isInitialized) {
            if (this.JDBC) {
                categoryService = CategoryDataServiceJDBCImpl(
                    connection = TODO()
                )
            }
            else {
                categoryService = CategoryDataServiceJPAImpl(
                    em = TODO(),
                    entityClass = TODO()
                )
            }
        }
        return categoryService
    }

    fun getProductDataService() : ProductService {
        if (!::productService.isInitialized) {
            if (this.JDBC) {
                productService = ProductDataServiceJDBCImpl(
                    connection = TODO(),
                    categoryService = TODO()
                )
            }
            else {
                productService = ProductDataServiceJPAImpl(
                    em = TODO(),
                    entityClass = TODO()
                )
            }
        }
        return productService
    }
}