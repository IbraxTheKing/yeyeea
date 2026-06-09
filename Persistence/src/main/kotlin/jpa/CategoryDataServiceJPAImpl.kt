package jpa

import dataservice.CategoryDataService
import entities.Category
import jakarta.persistence.EntityManager

class CategoryDataServiceJPAImpl(em: EntityManager,
                                 entityClass: Class<Category>
) : CategoryDataService, JpaDao<Category>(em, entityClass) {

    override fun getSubCategories(category: Category): List<Category> {
        return em.createQuery(
            "SELECT c FROM Category c WHERE c.parentCategory = :category",
            Category::class.java
        )
            .setParameter("category", category)
            .resultList
    }

    override fun getParentCategories(category: Category): List<Category> {
        val result = mutableListOf<Category>()

        var current = category.parentCategory

        while (current != null) {
            result.add(current)
            current = current.parentCategory
        }

        return result
    }
}