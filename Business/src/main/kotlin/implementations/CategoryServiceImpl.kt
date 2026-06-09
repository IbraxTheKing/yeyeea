package implementations

import dataservice.PersistenceFactory
import entities.Category
import services.CategoryService

class CategoryServiceImpl : CategoryService {

    private val categoryService: CategoryService = PersistenceFactory().getCategoryDataService()

    override fun getSubCategories(category: Category): List<Category>? {
        return categoryService.getSubCategories(category)
    }

    override fun getParentCategories(category: Category): List<Category>? {
        return categoryService.getParentCategories(category)
    }

    override fun add(t: Category) {
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