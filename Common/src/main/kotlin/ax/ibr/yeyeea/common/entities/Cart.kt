package ax.ibr.yeyeea.common.entities

import jakarta.persistence.*

@Entity
open class Cart(user: User) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToOne
    @JoinColumn(name = "user_id")
    var owner: User? = user

    var products: MutableList<Product> = mutableListOf()

    fun addProduct(product: Product) {
        products.add(product)
    }

    fun removeProduct(product: Product) {
        products.remove(product)
    }

}