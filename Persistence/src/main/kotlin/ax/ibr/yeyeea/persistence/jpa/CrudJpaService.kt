package ax.ibr.yeyeea.persistence.jpa

import jakarta.persistence.EntityManager
import ax.ibr.yeyeea.common.services.CrudService

open class CrudJpaService<T : Any>(
    protected val em: EntityManager,
    private val entityClass: Class<T>
) : CrudService<T> {

    override fun add(t: T) {
        em.transaction.begin()
        em.persist(t)
        em.transaction.commit()
    }

    override fun update(t: T) {
        em.transaction.begin()
        em.merge(t)
        em.transaction.commit()
    }

    override fun remove(t: T) {
        em.transaction.begin()
        val managed = if (em.contains(t)) t else em.merge(t)
        em.remove(managed)
        em.transaction.commit()
    }

    override fun getAll(): List<T> {
        return em.createQuery(
            "SELECT e FROM ${entityClass.simpleName} e",
            entityClass
        ).resultList
    }

    override fun getById(id: Long): T? {
        return em.find(entityClass, id)
    }
}