package com.bk.library.api.blogs.service

import com.bk.library.api.blogs.model.Blog

internal interface BlogRepository {
    suspend fun saveBlog(userId: String, title: String, description:String): Blog
    suspend fun updateBlog(blog: Blog)

    suspend fun getAllBlogs(): List<Blog>
    suspend fun getAllBlogs(userId: String): List<Blog>
    suspend fun getBlog(userId: String, blogId: Long): Blog?
    suspend fun getBlog(blogId: Long): Blog?


}