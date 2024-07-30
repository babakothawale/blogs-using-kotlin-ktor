package com.bk.library.api.identity.db

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureUserDatabase() {
    transaction {
        SchemaUtils.create(UserTable)
        SchemaUtils.create(SessionTable)
    }
}