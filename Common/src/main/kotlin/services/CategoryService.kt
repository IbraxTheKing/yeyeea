package services

import entities.Category

interface CategoryService : CrudService<Category> {
    fun getSubCategories(category: Category): List<Category>?

    fun getParentCategories(category: Category): List<Category>?

}