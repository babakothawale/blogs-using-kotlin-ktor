package com.bk.library.api.blogs.model

import kotlinx.serialization.Serializable

@Serializable
data class Blog(
    val blogId: Long,
    val userId: String,
    val title: String,
    val description: String,
    val created: Long,
    val updated: Long,
)

@Serializable
data class RequestBlogData(
    val blogId: Long,
    val title: String,
    val description: String
)


@Serializable
data class BlogRequest(
    val blogData: RequestBlogData)

@Serializable
data class BlogResponse(val blogs: List<Blog>, val message: String)