package com.bk.library.business.books

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.bookRoutes() {
    routing {
        staticResources("/content", "mycontent")
        get("/") {
            call.respondText("Hello World!")
        }
        get("/books") {
            val text = "<h1>This is book list</h1>"
            val contentType = ContentType.parse("text/html")
            call.respondText(text, contentType)
        }
        get("/error-test") {
            throw IllegalStateException("Too Busy")
        }
    }
}

fun Application.taskRoutes() {
    routing {
        get("/tasks") {
            call.respondText(
                contentType = ContentType.parse("text/html"),
                text = """
                    <h3>TODO:</h3>
                     <ol>
                    <li>A table of all the tasks</li>
                    <li>A form to submit new tasks</li>
                </ol>
                """.trimIndent()

            )
        }
    }
}