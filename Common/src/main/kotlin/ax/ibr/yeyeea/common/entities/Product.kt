package ax.ibr.yeyeea.common.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne

/**
 * @author ib <pro.ibr.ben@gmail.com>
 */

@Entity
open class Product {

    var price: Float? = null
    var description: String? = null
    var name: String? = null
    var image: String? = null

    var company: String? = null
    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: Category? = null

    @OneToOne
    @JoinColumn(name = "vendor_id")
    var vendor: User? = null


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    constructor() {
        image = "http://www.konvertra.com/sites/default/files/default_images/default_product.jpg"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (price != other.price) return false
        if (description != other.description) return false
        if (name != other.name) return false
        if (image != other.image) return false
        if (company != other.company) return false
        if (category != other.category) return false

        return true
    }

    override fun hashCode(): Int {
        var result = price?.hashCode() ?: 0
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (image?.hashCode() ?: 0)
        result = 31 * result + (company?.hashCode() ?: 0)
        result = 31 * result + (category?.hashCode() ?: 0)
        return result
    }


}