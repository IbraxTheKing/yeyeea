package ax.ibr.yeyeea.common.entities

import jakarta.persistence.Entity

@Entity
enum class UserType {
    CUSTOMER,OWNER,ADMIN
}