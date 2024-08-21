package com.bk.library.api.blogs.service

import com.bk.library.api.blogs.model.Blog
import com.bk.library.api.blogs.model.BlogRequest

internal class BlogService(private val blogRepository: BlogRepository) {

    suspend fun getAllBlogs(): List<Blog> {
        return blogRepository.getAllBlogs()
    }

    suspend fun getBlogById(id: Long): Blog? {
        return blogRepository.getBlog(id)
    }

    suspend fun saveBlog(userId: String, blogRequest: BlogRequest): Blog {
        return if (blogRequest.blogData.blogId > 0) {
//            blogRepository.updateBlog(newBlog)
            blogRepository.getBlog(blogRequest.blogData.blogId)!!
        } else {
            blogRepository.saveBlog(
                userId,
                title = blogRequest.blogData.title,
                description = blogRequest.blogData.description
            )
        }
    }

}