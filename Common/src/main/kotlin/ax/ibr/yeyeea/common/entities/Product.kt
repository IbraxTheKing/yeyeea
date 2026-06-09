package ax.ibr.yeyeea.common.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
open class Product {

    var price: Float? = null
    var description: String? = null
    var name: String? = null

    var company: String? = null
    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: Category? = null


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null


}