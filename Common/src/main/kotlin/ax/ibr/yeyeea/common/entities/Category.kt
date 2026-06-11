package ax.ibr.yeyeea.common.entities

import jakarta.persistence.*

/**
 * @author ib <pro.ibr.ben@gmail.com>
 */

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
    /**
     * description to specific categories:
     * A computer has ram, disk etc. add them here.
     */
    @MapKeyColumn(name = "description_key")
    @Column(name = "description_value")
    var categoryDescriptions: MutableMap<String, String> = mutableMapOf()


    /**
     * Adds description to category
     *
     * @param obj will take the variable type as the key title and its value as value.
     * You should use this function **only** if a special class has been created.
     *
     */
    fun addDescription(obj: Any) {
        categoryDescriptions[obj::class.simpleName ?: "Unknown"] = obj.toString()
    }

    /**
     * @param name key title
     * @param value
     */
    fun addDescription(name: String, value: Any) {
        categoryDescriptions[name] = value.toString()
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