package com.bk.library.api.blogs.service

import com.bk.library.api.blogs.model.Blog

class BlogService(private val blogRepository: BlogRepository) {

    suspend fun getAllBlogs(): List<Blog> {
        return blogRepository.getAllBlogs()
    }

    suspend fun getBlogById(id: Long): Blog? {
        return blogRepository.getBlog(id)
    }

    suspend fun saveBlog(blogRequest: Blog): Blog {
        return if (blogRequest.blogId > 0) {
            blogRepository.updateBlog(blogRequest)
            blogRepository.getBlog(blogRequest.blogId)!!
        } else {
            blogRepository.saveBlog(blogRequest)
        }
    }

}