package services

interface CrudService<T>{
    fun add(t: T)
    fun update(t: T)
    fun remove(t: T)
    fun getAll(): List<T>
    fun getById(id: Long): T?

}