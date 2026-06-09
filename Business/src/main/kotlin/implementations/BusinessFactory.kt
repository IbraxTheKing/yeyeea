package implementations

import dataservice.PersistenceFactory
import services.CategoryService
import services.ProductService
import services.UserService

class BusinessFactory {
    lateinit var userService: UserService
    lateinit var categoryService: CategoryService
    lateinit var productService: ProductService

    fun getUserService() : UserService {
        if (!::userService.isInitialized) {
            userService = UserServiceImpl()
        }
        return userService
    }

    fun getCategoryService() : CategoryService {
        if (!::categoryService.isInitialized) {
            categoryService = CategoryServiceImpl()
        }
        return categoryService
    }

    fun getProductService() : ProductService {
        if (!::productService.isInitialized) {
            productService = ProductServiceImpl()
        }
        return productService
    }
}