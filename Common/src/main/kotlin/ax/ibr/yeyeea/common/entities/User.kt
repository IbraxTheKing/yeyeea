package ax.ibr.yeyeea.common.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

/**
 * @author ib <pro.ibr.ben@gmail.com>
 */

@Entity
open class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var username: String? = null
    var password: String? = null

    var image: String? = "assets/defaults/profile.png"

    var email: String? = null

    var type: UserType? = UserType.CUSTOMER


    constructor() // TODO: REMOVE THIS SHIT
    public constructor(username: String?, password: String?, email: String?) {
        this.username = username
        this.password = password
        this.email = email
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (username != other.username) return false
        if (password != other.password) return false
        if (image != other.image) return false
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        var result = username?.hashCode() ?: 0
        result = 31 * result + (password?.hashCode() ?: 0)
        result = 31 * result + (image?.hashCode() ?: 0)
        result = 31 * result + (email?.hashCode() ?: 0)
        return result
    }
}