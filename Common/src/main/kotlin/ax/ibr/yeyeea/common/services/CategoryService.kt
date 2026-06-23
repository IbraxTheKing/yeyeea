package ax.ibr.yeyeea.common.services

import ax.ibr.utils.services.CrudService
import ax.ibr.yeyeea.common.entities.Category

/**
 * @author ib <pro.ibr.ben@gmail.com>
 */

interface CategoryService : CrudService<Category> {
    fun getSubCategories(category: Category): List<Category>?
    fun getParentCategories(category: Category): List<Category>?
    fun getByName(name: String): Category?

}