package ax.ibr.yeyeea.common.entities

/**
 * @author ib <pro.ibr.ben@gmail.com>
 */

enum class UserType(val id: Int) {
    OWNER(0),
    ADMIN(1),
    CUSTOMER(2),
    VENDOR(3)
}