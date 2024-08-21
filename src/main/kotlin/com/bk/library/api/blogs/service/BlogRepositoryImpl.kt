package com.bk.library.api.blogs.service

import com.bk.library.api.blogs.db.BlogDAO
import com.bk.library.api.blogs.db.BlogTable
import com.bk.library.api.blogs.db.daoToModel
import com.bk.library.api.blogs.db.toBlogModel
import com.bk.library.api.blogs.model.Blog
import com.bk.library.api.suspendTransaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.update

class BlogRepositoryImpl : BlogRepository {
    override suspend fun saveBlog(userId: String, title:String, description: String): Blog = suspendTransaction {
        BlogDAO.new {
            this.userId = userId
            this.title = title
            this.description = description
            created = System.currentTimeMillis()
            updated = created
        }.toBlogModel()
    }

    override suspend fun updateBlog(blog: Blog): Unit = suspendTransaction {
        BlogTable.update({ BlogTable.id eq blog.blogId }) {
            it[title] = blog.title
            it[description] = blog.description
            it[updated] = System.currentTimeMillis()
        }
    }

    override suspend fun getAllBlogs(): List<Blog> = suspendTransaction {
        BlogDAO.all().map(::daoToModel)
    }

    override suspend fun getAllBlogs(userId: String): List<Blog> = suspendTransaction {
        BlogDAO.find(BlogTable.userId eq userId).map(::daoToModel)
    }

    override suspend fun getBlog(userId: String, blogId: Long): Blog? = suspendTransaction {
        BlogDAO.find { (BlogTable.id eq blogId) and (BlogTable.userId eq userId) }.limit(1).map(::daoToModel)
            .firstOrNull()
    }

    override suspend fun getBlog(blogId: Long): Blog? = suspendTransaction {
        BlogDAO.find { (BlogTable.id eq blogId) }.limit(1).map(::daoToModel)
            .firstOrNull()
    }
}