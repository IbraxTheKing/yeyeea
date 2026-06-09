package implementations

import dataservice.PersistenceFactory
import entities.Category
import entities.Product
import services.ProductService

class ProductServiceImpl : ProductService {

    private val productService: ProductService = PersistenceFactory().getProductDataService()

    override fun getByName(name: String): List<Product> {
        return productService.getByName(name)
    }

    override fun getByCategory(category: Category): List<Product> {
        return productService.getByCategory(category)
    }

    override fun getByPrice(price: Float): List<Product> {
        return productService.getByPrice(price)
    }

    override fun getByPriceRange(range: ClosedRange<Float>): List<Product> {
        return productService.getByPriceRange(range)
    }

    override fun add(t: Product) {
        productService.add(t)
    }

    override fun update(t: Product) {
        productService.update(t)
    }

    override fun remove(t: Product) {
        productService.remove(t)
    }

    override fun getAll(): List<Product> {
        return productService.getAll()
    }

    override fun getById(id: Long): Product? {
        return productService.getById(id)
    }
}