package com.bk.library.api.blogs

import com.bk.library.api.blogs.db.configureBlogDatabase
import com.bk.library.api.blogs.service.BlogRepositoryImpl
import com.bk.library.api.blogs.service.BlogService
import io.ktor.server.application.*
import io.ktor.server.plugins.ratelimit.*
import io.ktor.server.routing.*

internal fun Application.blogModule() {
    configureBlogDatabase()
    routing {
        rateLimit(RateLimitName("protected")) {
            blogApiRoutes(BlogService(BlogRepositoryImpl()))
        }
    }
}