package jpa

import dataservice.ProductDataService
import entities.Category
import entities.Product
import jakarta.persistence.EntityManager

class ProductDataServiceJPAImpl(em: EntityManager,
                                entityClass: Class<Product>
) : ProductDataService, JpaDao<Product>(em, entityClass) {

    override fun getByName(name: String): List<Product> {
        return em.createQuery(
            "SELECT p FROM Product p WHERE p.name = :name",
            Product::class.java
        )
            .setParameter("name", name)
            .resultList
    }

    override fun getByCategory(category: Category): List<Product> {
        return em.createQuery(
            "SELECT p FROM Product p WHERE p.category = :category",
            Product::class.java
        )
            .setParameter("category", category)
            .resultList
    }

    override fun getByPrice(price: Float): List<Product> {
        return em.createQuery(
            "SELECT p FROM Product p WHERE p.price = :price",
            Product::class.java
        )
            .setParameter("price", price)
            .resultList
    }

    override fun getByPriceRange(range: ClosedRange<Float>): List<Product> {
        return em.createQuery(
            "SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max",
            Product::class.java
        )
            .setParameter("min", range.start)
            .setParameter("max", range.endInclusive)
            .resultList
    }

}