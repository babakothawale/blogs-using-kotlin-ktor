package com.bk.library.api.identity.db

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

internal object SessionTable : LongIdTable("session") {
    val username = varchar("username", 50)
    val token = varchar("token", 255)
    val validTill = long("till")
    val created = long("created")
    val updated = long("updated")
}

internal class SessionDAO(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<SessionDAO>(SessionTable)

    var username by SessionTable.username
    var token by SessionTable.token
    var validTill by SessionTable.validTill
    var created by SessionTable.created
    var updated by SessionTable.updated
}