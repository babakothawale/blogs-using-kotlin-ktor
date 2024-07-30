package com.bk.library.api.blogs.db

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureBlogDatabase() {
//    Database.connect(
//        "jdbc:postgresql://localhost:5432/ktor_tutorial_db",
//        user = "postgres",
//        password = "password"
//    )
//    Database.connect("jdbc:h2:file:./db/ktorblog;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE", driver = "org.h2.Driver", user = "root", password = "")
    transaction {
        SchemaUtils.create(BlogTable)
    }
}