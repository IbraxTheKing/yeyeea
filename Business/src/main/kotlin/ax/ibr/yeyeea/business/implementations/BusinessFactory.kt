package ax.ibr.yeyeea.business.implementations

import ax.ibr.yeyeea.common.services.CartService
import ax.ibr.yeyeea.common.services.CategoryService
import ax.ibr.yeyeea.common.services.ProductService
import ax.ibr.yeyeea.common.services.UserService

class BusinessFactory {
    private lateinit var userService: UserService
    private lateinit var categoryService: CategoryService
    private lateinit var productService: ProductService
    private lateinit var cartService: CartService

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

    fun getCartService(): CartService {
        if (!::cartService.isInitialized) {
            cartService = CartServiceImpl()
        }
        return cartService
    }
}