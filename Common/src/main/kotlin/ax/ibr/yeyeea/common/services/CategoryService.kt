package ax.ibr.yeyeea.common.services

import ax.ibr.yeyeea.common.entities.Category

interface CategoryService : CrudService<Category> {
    fun getSubCategories(category: Category): List<Category>?

    fun getParentCategories(category: Category): List<Category>?

}