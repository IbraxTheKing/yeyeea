package ax.ibr.yeyeea.persistence.dataservice

import ax.ibr.yeyeea.persistence.jdbc.CategoryDataServiceJDBCImpl
import ax.ibr.yeyeea.persistence.jdbc.ProductDataServiceJDBCImpl
import ax.ibr.yeyeea.persistence.jdbc.UserDataServiceJDBCImpl
import ax.ibr.yeyeea.persistence.jpa.CategoryDataServiceCrudJPAImpl
import ax.ibr.yeyeea.persistence.jpa.ProductDataServiceCrudJPAImpl
import ax.ibr.yeyeea.persistence.jpa.UserDataServiceCrudJPAImpl
import ax.ibr.yeyeea.common.services.CategoryService
import ax.ibr.yeyeea.common.services.ProductService
import ax.ibr.yeyeea.common.services.UserService

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
                userService = UserDataServiceCrudJPAImpl(
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
                categoryService = CategoryDataServiceCrudJPAImpl(
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
                productService = ProductDataServiceCrudJPAImpl(
                    em = TODO(),
                    entityClass = TODO()
                )
            }
        }
        return productService
    }
}