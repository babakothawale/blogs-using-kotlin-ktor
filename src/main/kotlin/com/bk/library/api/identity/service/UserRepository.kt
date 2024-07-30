package com.bk.library.api.identity.service

import com.bk.library.api.identity.db.UserDAO
import com.bk.library.api.identity.db.UserTable
import com.bk.library.api.identity.db.daoToModel
import com.bk.library.api.identity.model.User
import com.bk.library.api.suspendTransaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq


interface UserRepository {
    suspend fun register(user: User, password: String): Long
    suspend fun find(username: String): User?
    suspend fun isUserExists(username: String, email: String)
}


class UserRepositoryImpl : UserRepository {
    override suspend fun register(user: User, password: String): Long = suspendTransaction {
        findAndThrow(username = user.username, email = user.email)
        //TODO: encrypt password
        UserDAO.new {
            username = user.username
            pass = password
            email = user.email
            google = user.google
            created = System.currentTimeMillis()
            updated = created
        }.id.value
    }

    override suspend fun find(username: String): User? = suspendTransaction {
        UserDAO.find { (UserTable.username eq username) }.limit(1).map(::daoToModel).firstOrNull()
    }

    override suspend fun isUserExists(username: String, email: String) = suspendTransaction {
        findAndThrow(username, email)
    }

    private fun findAndThrow(username: String, email: String) {
        val withUsername = UserDAO.find((UserTable.username eq username)).firstOrNull()
        if (withUsername != null) {
            throw Exception("User already exists with the username")
        }
        val withEmail = UserDAO.find((UserTable.email eq email)).firstOrNull()
        if (withEmail != null) {
            throw Exception("User already exists with the email")
        }
    }

}