package ax.ibr.yeyeea.persistence.jdbc

import ax.ibr.yeyeea.common.entities.Cart
import ax.ibr.yeyeea.common.entities.User
import ax.ibr.yeyeea.persistence.dataservice.CartDataService
import java.sql.Connection
import java.sql.ResultSet

class CartDataServiceJDBCImpl(
    connection: Connection
) : CartDataService, JdbcDao<Cart>(connection, "cart") {

    override fun getByUser(user: User): Cart? {
        TODO("Not yet implemented")
    }

    override fun getByUserId(userId: Long): Cart? {
        TODO("Not yet implemented")
    }

    override fun mapRow(rs: ResultSet): Cart {
        TODO("Not yet implemented")
    }

    override fun insertSql(t: Cart): Pair<String, List<Any?>> {
        TODO("Not yet implemented")
    }

    override fun updateSql(t: Cart): Pair<String, List<Any?>> {
        TODO("Not yet implemented")
    }

    override fun idOf(t: Cart): Long? {
        TODO("Not yet implemented")
    }
}