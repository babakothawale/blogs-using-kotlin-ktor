package com.bk.library.api.identity

import com.bk.library.api.identity.db.configureUserDatabase
import com.bk.library.api.identity.service.*
import com.bk.library.api.identity.service.SessionRepositoryImpl
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.ratelimit.*
import io.ktor.server.routing.*

internal fun Application.identityModule() {
    val sessionValidity: Long =
        environment.config.property("ktor.application.session_validity").getString().toLongOrNull() ?: 1440

    val session = SessionRepositoryImpl((sessionValidity * 60000))
    configureUserDatabase()
    install(Authentication) {
        bearer("auth-bearer") {
            realm = "Access to the '/' path"
            authenticate { credentials ->
                val userSession = session.isSessionValid(credentials.token)
                if (userSession != null) {
                    UserIdPrincipal(userSession)
                } else {
                    null
                }
            }
        }
    }
    routing {
        rateLimit(RateLimitName("critical")) {
            identityApiRoutes(LoginService(UserRepositoryImpl(), session))
        }
    }
}