package ax.ibr.yeyeea.common.entities

/**
 * @author ib <pro.ibr.ben@gmail.com>
 */

enum class UserType(val id: Int) {
    CUSTOMER(2),
    OWNER(0),
    ADMIN(1),
    VENDOR(3)
}