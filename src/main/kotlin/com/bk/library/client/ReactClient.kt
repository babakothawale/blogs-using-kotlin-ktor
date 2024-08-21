package com.bk.library.client

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.reactClientAppModule() {
    routing {
        singlePageApplication {
            useResources = true
            filesPath = "library-web-app"
            defaultPage = "index.html"
//            ignoreFiles { it.endsWith(".txt") }
        }
    }
}