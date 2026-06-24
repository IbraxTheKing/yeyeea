package ax.ibr.yeyeea.common.services

import ax.ibr.utils.services.CrudService
import ax.ibr.yeyeea.common.entities.Category
import ax.ibr.yeyeea.common.entities.Product
import ax.ibr.yeyeea.common.entities.User

/**
 * @author ib <pro.ibr.ben@gmail.com>
 */

interface ProductService : CrudService<Product> {
    fun getByName(name: String): List<Product>
    fun getByCategory(category: Category): List<Product>
    fun getByPrice(price: Float): List<Product>
    fun getByPriceRange(range: ClosedRange<Float>): List<Product>
    fun getByVendor(user: User): List<Product>

}