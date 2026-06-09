package entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Product {

    var price: Float? = null
    var description: String? = null
    var name: String? = null

    var company: String? = null
    var category: Category? = null


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null


}