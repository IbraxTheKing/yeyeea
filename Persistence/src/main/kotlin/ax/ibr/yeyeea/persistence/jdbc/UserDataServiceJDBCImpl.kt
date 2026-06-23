package ax.ibr.yeyeea.persistence.jdbc

import ax.ibr.yeyeea.persistence.dataservice.UserDataService
import ax.ibr.yeyeea.common.entities.User
import java.sql.Connection
import java.sql.ResultSet

class UserDataServiceJDBCImpl(
    connection: Connection
) : UserDataService, JdbcDao<User>(connection, "user") {

    override fun mapRow(rs: ResultSet): User {
        return User().apply {
            id       = rs.getLong("id").takeIf { !rs.wasNull() }
            username = rs.getString("username")
            password = rs.getString("password")
            email    = rs.getString("email")
            image    = rs.getString("image")
        }
    }

    override fun insertSql(t: User): Pair<String, List<Any?>> {
        val sql = """
            INSERT INTO $tableName (username, password, email, image)
            VALUES (?, ?, ?, ?)
        """.trimIndent()
        return sql to listOf(t.username, t.password, t.email, t.image)
    }

    override fun updateSql(t: User): Pair<String, List<Any?>> {
        val sql = """
            UPDATE $tableName
            SET username = ?, password = ?, email = ?, image = ?
            WHERE id = ?
        """.trimIndent()
        return sql to listOf(t.username, t.password, t.email, t.image, t.id)
    }

    override fun idOf(t: User): Long? = t.id

    override fun getByUsername(username: String?): List<User> {
        return query("SELECT * FROM $tableName WHERE username = ?", username)
    }

    override fun getByEmail(email: String): List<User> {
        return query("SELECT * FROM $tableName WHERE email = ?", email)
    }
}
