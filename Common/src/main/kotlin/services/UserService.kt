package services

import entities.User

interface UserService : CrudService<User> {
    fun getByUsername(username: String): List<User>
    fun getByEmail(email: String): List<User>
}