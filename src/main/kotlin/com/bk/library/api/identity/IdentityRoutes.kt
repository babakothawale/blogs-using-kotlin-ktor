package com.bk.library.api.identity

import com.bk.library.api.identity.model.LoginRequest
import com.bk.library.api.identity.model.RegisterUserRequest
import com.bk.library.api.identity.service.LoginService
import com.bk.library.api.respondCss
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*
import kotlinx.css.*

internal fun Route.identityApiRoutes(loginService: LoginService) {
    route("/api") {
        post("/register") {
            val request = call.receive<RegisterUserRequest>()
            val response = loginService.register(request)
            call.respond(response)
        }
        post("/login") {
            val request = call.receive<LoginRequest>()
            val response = loginService.login(request)
            call.respond(response)
        }

    }
}

internal fun Route.identityAppRoutes() {
    route("/id") {
        get("/style.css") {
            call.respondCss {
                body {
                    backgroundColor = Color.darkBlue
                    margin = Margin(0.px)
                }
                rule("h1.page-title") {
                    color = Color.white
                }
            }
        }
        get("/signup") {
            call.respondHtml {
                head {
                    link(rel = "stylesheet", href = "/id/style.css", type = "text/css")
                }
                body {
                    h1(classes = "page-title") {
                        +"Hello from Ktor!"
                    }
                    form(
                        action = "/login",
                        encType = FormEncType.applicationXWwwFormUrlEncoded,
                        method = FormMethod.post
                    ) {
                        p {
                            +"Username:"
                            textInput(name = "username")
                        }
                        p {
                            +"Password:"
                            passwordInput(name = "password")
                        }
                        p {
                            submitInput() { value = "Login" }
                        }
                    }
                }
            }
        }
        get("/signin") {
            call.respondText(text = "Sign in form will be loaded")
        }
    }
}