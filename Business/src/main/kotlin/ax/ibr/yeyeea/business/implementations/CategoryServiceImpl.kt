package ax.ibr.yeyeea.business.implementations

import ax.ibr.utils.exceptions.AlreadyExistsException
import ax.ibr.yeyeea.persistence.dataservice.PersistenceFactory
import ax.ibr.yeyeea.common.entities.Category
import ax.ibr.yeyeea.common.services.CategoryService

class CategoryServiceImpl : CategoryService {

    private val categoryService: CategoryService = PersistenceFactory().getCategoryDataService()

    override fun getByName(name: String): Category? {
        return categoryService.getByName(name)
    }

    override fun getSubCategories(category: Category): List<Category>? {
        return categoryService.getSubCategories(category)
    }

    override fun getParentCategories(category: Category): List<Category>? {
        return categoryService.getParentCategories(category)
    }

    override fun add(t: Category) {
        val existingCategory = categoryService.getByName(t.name!!)

        if (existingCategory != null) {
            throw AlreadyExistsException("Category ${t.name} already exists")
        }
        categoryService.add(t)
    }

    override fun update(t: Category) {
        categoryService.update(t)
    }

    override fun remove(t: Category) {
        categoryService.remove(t)
    }

    override fun getAll(): List<Category> {
        return categoryService.getAll()
    }

    override fun getById(id: Long): Category? {
        return categoryService.getById(id)
    }
}