package com.bk.library.api.blogs

import com.bk.library.api.blogs.model.BlogRequest
import com.bk.library.api.blogs.model.BlogResponse
import com.bk.library.api.blogs.service.BlogService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.blogApiRoutes(blogService: BlogService) {
    authenticate("auth-bearer") {
        route("/api/blog") {
            get {
                call.respond(blogService.getAllBlogs())
            }
            get("/{id}") {
                val id = call.parameters["id"]?.toLongOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, "Provide blog id")
                } else {
                    val blog = blogService.getBlogById(id)
                    if (blog == null) {
                        call.respond(HttpStatusCode.NotFound)
                    } else {
                        call.respond(HttpStatusCode.OK, BlogResponse(listOf(blog), ""))
                    }
                }
            }

            post {
                try {
                    val userId = call.principal<UserIdPrincipal>()?.name
                    if (userId.isNullOrEmpty()) {
                        call.respond(HttpStatusCode.BadRequest, "Unauthorised")
                    }
                    val blogRequest = call.receive<BlogRequest>()
                    if (blogRequest.isEmpty()) {
                        call.respond(HttpStatusCode.BadRequest, "Blog has no data")
                    } else {

                        val blogRes = blogService.saveBlog(userId = userId!!, blogRequest)
                        call.respond(HttpStatusCode.OK, blogRes)
                    }
                } catch (ex: IllegalArgumentException) {
                    call.respond(HttpStatusCode.BadRequest)
                } catch (ex: IllegalStateException) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
    }
}
