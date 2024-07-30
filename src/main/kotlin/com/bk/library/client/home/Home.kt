package com.bk.library.client.home

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Route.appRoutes() {
    staticResources("static", "static")
    staticFiles("static", File("static"))
    get("/") {
        val text = "<head><link rel=\"stylesheet\" href=\"/static/style.css\"></head>" +
                "<h3 class='red'>Hello, Welcome to blog site!</h3>"
        val type = ContentType.parse("text/html")
        call.respondText(
            text = text,
            contentType = type
        )
    }
}