package implementations

import dataservice.PersistenceFactory
import services.CategoryService
import services.ProductService
import services.UserService

class BusinessFactory {
    private lateinit var userService: UserService
    private lateinit var categoryService: CategoryService
    private lateinit var productService: ProductService

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