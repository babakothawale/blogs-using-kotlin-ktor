package com.bk.library

import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*

fun main(args: Array<String>) {
    EngineMain.main(args)
//    embeddedServer(Netty, port = 3000, host = " 0.0.0.0") {
//        routing {
//            singlePageApplication{
//                react()
//            }
//        }
//    }

}
