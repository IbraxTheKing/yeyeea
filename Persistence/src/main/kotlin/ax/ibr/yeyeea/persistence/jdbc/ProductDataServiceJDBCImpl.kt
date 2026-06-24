package ax.ibr.yeyeea.persistence.jdbc

import ax.ibr.yeyeea.common.entities.Category
import ax.ibr.yeyeea.common.entities.Product
import ax.ibr.yeyeea.common.entities.User
import ax.ibr.yeyeea.persistence.dataservice.ProductDataService
import java.sql.Connection
import java.sql.ResultSet

class ProductDataServiceJDBCImpl(
    connection: Connection,
    private val categoryService: CategoryDataServiceJDBCImpl
) : ProductDataService, JdbcDao<Product>(connection, "product") {

    override fun mapRow(rs: ResultSet): Product {
        return Product().apply {
            id          = rs.getLong("id").takeIf { !rs.wasNull() }
            name        = rs.getString("name")
            description = rs.getString("description")
            price       = rs.getFloat("price").takeIf { !rs.wasNull() }
            company     = rs.getString("company")

            val categoryId = rs.getLong("category_id").takeIf { !rs.wasNull() }
            if (categoryId != null) {
                category = categoryService.getById(categoryId)
            }
        }
    }

    override fun insertSql(t: Product): Pair<String, List<Any?>> {
        val sql = """
            INSERT INTO $tableName (name, description, price, company, category_id)
            VALUES (?, ?, ?, ?, ?)
        """.trimIndent()
        return sql to listOf(t.name, t.description, t.price, t.company, t.category?.id)
    }

    override fun updateSql(t: Product): Pair<String, List<Any?>> {
        val sql = """
            UPDATE $tableName
            SET name = ?, description = ?, price = ?, company = ?, category_id = ?
            WHERE id = ?
        """.trimIndent()
        return sql to listOf(t.name, t.description, t.price, t.company, t.category?.id, t.id)
    }

    override fun idOf(t: Product): Long? = t.id

    override fun getByName(name: String): List<Product> {
        return query("SELECT * FROM $tableName WHERE name = ?", name)
    }

    override fun getByCategory(category: Category): List<Product> {
        return query("SELECT * FROM $tableName WHERE category_id = ?", category.id)
    }

    override fun getByPrice(price: Float): List<Product> {
        return query("SELECT * FROM $tableName WHERE price = ?", price)
    }

    override fun getByPriceRange(range: ClosedRange<Float>): List<Product> {
        return query(
            "SELECT * FROM $tableName WHERE price BETWEEN ? AND ?",
            range.start,
            range.endInclusive
        )
    }

    override fun getByVendor(user: User): List<Product> {
        TODO("Not yet implemented")
    }
}
