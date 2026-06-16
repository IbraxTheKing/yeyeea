package ax.ibr.yeyeea.persistence.jpa

import jakarta.persistence.EntityManager


open class CrudJpaService<T>(
    protected val em: EntityManager,
    private val entityClass: Class<T>

) {
    private var entityName: String? = entityClass.simpleName

    open fun add(t: T) {

        val tx = em.transaction

        try {

            tx.begin()

            em.persist(t)

            tx.commit()


        } catch(e: Exception) {

            if (tx.isActive)
                tx.rollback()

            throw e
        }
    }



    open fun update(t: T) {

        val tx = em.transaction

        try {

            tx.begin()

            em.merge(t)

            tx.commit()


        } catch(e: Exception) {

            if(tx.isActive)
                tx.rollback()

            throw e
        }
    }



    open fun remove(t: T) {

        val tx = em.transaction

        try {

            tx.begin()

            em.remove(
                if(em.contains(t))
                    t
                else
                    em.merge(t)
            )

            tx.commit()


        } catch(e: Exception) {

            if(tx.isActive)
                tx.rollback()

            throw e
        }
    }



    open fun getAll(): List<T> {

        return em.createQuery(
            "SELECT e FROM $entityName e",
            entityClass
        ).resultList

    }



    open fun getById(id: Long): T? {

        return em.find(
            entityClass,
            id
        )
    }
}