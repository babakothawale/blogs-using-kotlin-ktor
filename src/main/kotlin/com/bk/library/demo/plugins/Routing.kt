package com.bk.library.demo.plugins

import com.bk.library.demo.model.TaskRepository
import com.bk.library.demo.*
import io.ktor.server.application.*

fun Application.configureDemoRouting(repository: TaskRepository) {
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
