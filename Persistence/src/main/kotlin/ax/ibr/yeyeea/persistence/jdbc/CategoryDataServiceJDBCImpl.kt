package ax.ibr.yeyeea.persistence.jdbc

import ax.ibr.yeyeea.persistence.dataservice.CategoryDataService
import ax.ibr.yeyeea.common.entities.Category
import java.sql.Connection
import java.sql.ResultSet

class CategoryDataServiceJDBCImpl(
    connection: Connection
) : CategoryDataService, JdbcDao<Category>(connection, "category") {

    override fun mapRow(rs: ResultSet): Category {
        val category = Category().apply {
            id          = rs.getLong("id").takeIf { !rs.wasNull() }
            name        = rs.getString("name")
            description = rs.getString("description")
            isMainCategory = rs.getBoolean("is_main_category")
        }

        val parentId = rs.getLong("parent_id").takeIf { !rs.wasNull() }

        if (parentId != null) {
            category.parentCategory = getById(parentId)
        }

        return category
    }

    override fun insertSql(t: Category): Pair<String, List<Any?>> {
        val sql = """
            INSERT INTO $tableName (name, description, is_main_category, parent_id)
            VALUES (?, ?, ?, ?)
        """.trimIndent()
        return sql to listOf(t.name, t.description, t.isMainCategory, t.parentCategory?.id)
    }

    override fun updateSql(t: Category): Pair<String, List<Any?>> {
        val sql = """
            UPDATE $tableName
            SET name = ?, description = ?, is_main_category = ?, parent_id = ?
            WHERE id = ?
        """.trimIndent()
        return sql to listOf(t.name, t.description, t.isMainCategory, t.parentCategory?.id, t.id)
    }

    override fun idOf(t: Category): Long? = t.id

    override fun getSubCategories(category: Category): List<Category> {
        return query("SELECT * FROM $tableName WHERE parent_id = ?", category.id)
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

    override fun getByName(name: String): Category? {
        TODO("Not yet implemented")
    }
}
