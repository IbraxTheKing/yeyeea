package services

import entities.Category
import entities.Product

interface ProductService : CrudService<Product> {
    fun getByName(name: String): List<Product>
    fun getByCategory(category: Category): List<Product>
    fun getByPrice(price: Float): List<Product>
    fun getByPriceRange(range: ClosedRange<Float>): List<Product>

}