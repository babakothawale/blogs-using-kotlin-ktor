package com.bk.library.api.identity.db

import com.bk.library.api.identity.model.User
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

internal object UserTable : LongIdTable("user") {
    val username = varchar("username", 50)
    val pass = varchar("pass", 50)
    val email = varchar("email", 50)
    val google = bool("google")
    val created = long("created")
    val updated = long("updated")
}

internal class UserDAO(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UserDAO>(UserTable)
    var username by UserTable.username
    var pass by UserTable.pass
    var email by UserTable.email
    var google by UserTable.google
    var created by UserTable.created
    var updated by UserTable.updated
}


internal fun daoToModel(dao: UserDAO) = User(
    username = dao.username,
    email = dao.email,
    google = dao.google
)

internal fun UserDAO.toUser(): User {
    return daoToModel(this)
}