package ax.ibr.yeyeea.common.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

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

    private var type: UserType? = UserType.CUSTOMER

    constructor()
    public constructor(username: String?, password: String?, email: String?) {
        this.username = username
        this.password = password
        this.email = email
    }
}