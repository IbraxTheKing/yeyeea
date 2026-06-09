package dataservice

import jpa.CategoryDataServiceJPAImpl
import jpa.ProductDataServiceJPAImpl
import jpa.UserDataServiceJPAImpl
import services.CategoryService
import services.ProductService
import services.UserService

class PersistenceFactory {
    lateinit var userService: UserService
    lateinit var categoryService: CategoryService
    lateinit var productService: ProductService

    var JDBC: Boolean = false
    val PU: String = ""

    fun getUserDataService() : UserService {
        if (!::userService.isInitialized) {
            if (this.JDBC) {

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
            if (this.JDBC) {}
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
            if (this.JDBC) {}
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