package com.bk.library.api.identity

import com.bk.library.api.identity.model.LoginRequest
import com.bk.library.api.identity.model.RegisterUserRequest
import com.bk.library.api.identity.service.LoginService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

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