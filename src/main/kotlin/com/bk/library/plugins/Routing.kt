package com.bk.library.plugins

import com.bk.library.business.demo.*
import com.bk.library.business.demo.model.TaskRepository
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureRouting(repository: TaskRepository) {
//    install(StatusPages) {
//        exception<IllegalStateException> { call, cause ->
//            call.respondText("App in illegal state as ${cause.message}")
//        }
//    }
    demoRoutes()
    taskHtmlRoutes()
    taskRoutesREST()
    templateRoutes()
    socketRoutes()
    dbRouting(repository)

}
