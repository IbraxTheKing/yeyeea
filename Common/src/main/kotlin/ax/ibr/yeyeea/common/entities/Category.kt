package ax.ibr.yeyeea.common.entities

import jakarta.persistence.*

@Entity
open class Category() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String? = null

    var description: String? = null

    var isMainCategory: Boolean = false


    @ManyToOne
    @JoinColumn(name = "parent_id")
    var parentCategory: Category? = null


    @OneToMany(mappedBy = "parentCategory", cascade = [CascadeType.ALL])
    var subCategories: MutableList<Category> = mutableListOf()


    @ElementCollection
    @CollectionTable(
        name = "category_descriptions",
        joinColumns = [JoinColumn(name = "category_id")]
    )
    @MapKeyColumn(name = "description_key")
    @Column(name = "description_value")
    var categoryDescriptions: MutableMap<String, String> = mutableMapOf()

    fun addDescription(obj: Any) {
        categoryDescriptions[obj::class.simpleName ?: "Unknown"] = obj.toString()
    }

    override fun toString(): String {
        return buildString {
            appendLine("Category: $name")
            appendLine("Description: $description")

            categoryDescriptions.forEach { (key, value) ->
                appendLine("$key : $value")
            }
        }
    }
}