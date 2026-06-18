package ax.ibr.yeyeea.persistence.jdbc

import ax.ibr.utils.services.CrudService
import java.sql.Connection
import java.sql.ResultSet

abstract class JdbcDao<T : Any>(
    protected val connection: Connection,
    protected val tableName: String
) : CrudService<T> {

    protected abstract fun mapRow(rs: ResultSet): T
    protected abstract fun insertSql(t: T): Pair<String, List<Any?>>
    protected abstract fun updateSql(t: T): Pair<String, List<Any?>>
    protected abstract fun idOf(t: T): Long?

    override fun add(t: T) {
        val (sql, params) = insertSql(t)
        connection.prepareStatement(sql).use { stmt ->
            params.forEachIndexed { i, v -> stmt.setObject(i + 1, v) }
            stmt.executeUpdate()
        }
    }

    override fun update(t: T) {
        val (sql, params) = updateSql(t)
        connection.prepareStatement(sql).use { stmt ->
            params.forEachIndexed { i, v -> stmt.setObject(i + 1, v) }
            stmt.executeUpdate()
        }
    }

    override fun remove(t: T) {
        val id = idOf(t) ?: return
        connection.prepareStatement("DELETE FROM $tableName WHERE id = ?").use { stmt ->
            stmt.setLong(1, id)
            stmt.executeUpdate()
        }
    }

    override fun getAll(): List<T> {
        connection.prepareStatement("SELECT * FROM $tableName").use { stmt ->
            val rs = stmt.executeQuery()
            val results = mutableListOf<T>()
            while (rs.next()) results.add(mapRow(rs))
            return results
        }
    }

    override fun getById(id: Long): T? {
        connection.prepareStatement("SELECT * FROM $tableName WHERE id = ?").use { stmt ->
            stmt.setLong(1, id)
            val rs = stmt.executeQuery()
            return if (rs.next()) mapRow(rs) else null
        }
    }

    protected fun query(sql: String, vararg params: Any?): List<T> {
        connection.prepareStatement(sql).use { stmt ->
            params.forEachIndexed { i, v -> stmt.setObject(i + 1, v) }
            val rs = stmt.executeQuery()
            val results = mutableListOf<T>()
            while (rs.next()) results.add(mapRow(rs))
            return results
        }
    }
}
