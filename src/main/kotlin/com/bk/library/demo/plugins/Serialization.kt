package com.bk.library.demo.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configureDemoSerialization() {
    install(ContentNegotiation) {
        json()
    }
}