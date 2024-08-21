package com.bk.library

import io.ktor.server.application.*
import io.ktor.server.plugins.defaultheaders.*
import org.jetbrains.exposed.sql.Database

fun Application.defaultModule() {
    install(DefaultHeaders)
    connectDatabase()
}

//fun Application.defaults() {
//    install(DefaultHeaders)
////    install(Compression)
////    install(CachingHeaders) {
////        options { call, content ->
////            when (content.contentType?.withoutParameters()) {
////                ContentType.Text.Plain -> CachingOptions(CacheControl.MaxAge(maxAgeSeconds = 3600))
////                ContentType.Text.Html -> CachingOptions(CacheControl.MaxAge(maxAgeSeconds = 60))
////                else -> null
////            }
////        }
////    }
//}

private fun Application.connectDatabase() {
    Database.connect("jdbc:h2:file:./db/ktorblog;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE", driver = "org.h2.Driver", user = "root", password = "")
}