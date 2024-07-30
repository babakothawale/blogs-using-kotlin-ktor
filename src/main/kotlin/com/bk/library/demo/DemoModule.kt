package com.bk.library.demo

import com.bk.library.demo.model.PostgresTaskRepository
import com.bk.library.demo.plugins.*
import io.ktor.server.application.*

fun Application.demoModule() {
//    configureDemoAuthentication()
    configureDemoSerialization()
    configureDemoSockets()
    configureDemoTemplating()
    configureDemoDatabases()
    configureDemoRouting(PostgresTaskRepository())
}