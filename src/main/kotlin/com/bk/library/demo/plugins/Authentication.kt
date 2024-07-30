package com.bk.library.demo.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*

val digestFunction = getDigestFunction("SHA-256") { "ktor${it.length}" }
val hashedUserTable = UserHashedTableAuth(
    table = mapOf(
        "jetbrains" to digestFunction("foobar"),
        "admin" to digestFunction("password")
    ),
    digester = digestFunction
)

fun Application.configureDemoAuthentication() {
//    install(Authentication) {
//        basic("auth-basic") {
//            realm = "Access to the '/' path"
//            validate { userPasswordCredential ->
//                if (userPasswordCredential.name == "kothawaleb" && userPasswordCredential.password == "kothawaleb") {
//                    UserIdPrincipal(userPasswordCredential.name)
//                } else {
//                    null
//                }
//            }
//        }
//    }

    install(Authentication) {
        basic("auth-basic-hashed") {
            realm = "Access to the '/' path"
            validate { credentials ->
                hashedUserTable.authenticate(credentials)
            }
        }
    }

    routing {
//        authenticate("auth-basic") {
//            get("/auth-basic") {
//                call.respondText("auth-basic ::: Welcome abroad!!")
//            }
//        }

        authenticate("auth-basic-hashed") {
            get("auth-basic-hashed") {
                call.respondText("auth-basic-hashed ::: Welcome abroad!!")
            }
        }

    }
}