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
            PersistenceFactory().getUserDataService()
        }
        return userService
    }

    fun getCategoryService() : CategoryService {
        if (!::categoryService.isInitialized) {
            PersistenceFactory().getCategoryDataService()
        }
        return categoryService
    }

    fun getProductService() : ProductService {
        if (!::productService.isInitialized) {
            PersistenceFactory().getProductDataService()
        }
        return productService
    }
}