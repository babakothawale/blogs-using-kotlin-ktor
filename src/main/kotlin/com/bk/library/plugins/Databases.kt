package com.bk.library.plugins

import com.bk.library.business.demo.db.TaskTable
import com.bk.library.business.demo.model.Task
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
//    Database.connect(
//        "jdbc:postgresql://localhost:5432/ktor_tutorial_db",
//        user = "postgres",
//        password = "password"
//    )
    Database.connect("jdbc:h2:file:./db/ktorblog;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE", driver = "org.h2.Driver", user = "root", password = "")
    transaction {
        SchemaUtils.create(TaskTable)
    }
}