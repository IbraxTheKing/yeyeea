package ax.ibr.yeyeea.persistence.jpa

import ax.ibr.yeyeea.persistence.dataservice.CategoryDataService
import ax.ibr.yeyeea.common.entities.Category
import jakarta.persistence.EntityManager

class CategoryDataServiceCrudJPAImpl(em: EntityManager,
                                     entityClass: Class<Category>
) : CategoryDataService, CrudJpaService<Category>(em, entityClass) {

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