package ax.ibr.yeyeea.persistence.jpa

import ax.ibr.utils.services.jpa.CrudJpaService
import ax.ibr.yeyeea.common.entities.Category
import ax.ibr.yeyeea.persistence.dataservice.CategoryDataService
import jakarta.persistence.EntityManager

class CategoryDataServiceJPAImpl(em: EntityManager,
                                 entityClass: Class<Category>
) : CategoryDataService, CrudJpaService<Category>(em, entityClass) {

    override fun getSubCategories(category: Category): List<Category>? {
        return em.createQuery(
            "SELECT c FROM Category c WHERE c.parentCategory = :category",
            Category::class.java
        )
            .setParameter("category", category)
            .resultList
    }

    override fun getParentCategories(category: Category): List<Category>? {
        val result = mutableListOf<Category>()

        var current = category.parentCategory

        while (current != null) {
            result.add(current)
            current = current.parentCategory
        }

        return result
    }

    override fun getByName(name: String): Category? {
        return em.createQuery(
            "SELECT c FROM Category c WHERE c.name = :name",
            Category::class.java
        )
            .setParameter("name", name)
            .setMaxResults(1)
            .resultList
            .firstOrNull()
    }
}