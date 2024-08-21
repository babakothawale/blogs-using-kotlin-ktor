package com.bk.library.api.blogs.db

import com.bk.library.api.blogs.model.Blog
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

internal object BlogTable : LongIdTable("blog") {
    val userId = varchar("userId", 50)
    val title = varchar("title", 50)
    val description = varchar("description", 1048)
    val created = long("created")
    val updated = long("updated")
}

internal class BlogDAO(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<BlogDAO>(BlogTable)

    var userId by BlogTable.userId
    var title by BlogTable.title
    var description by BlogTable.description
    var created by BlogTable.created
    var updated by BlogTable.updated
}

internal fun daoToModel(dao: BlogDAO) = Blog(
    blogId = dao.id.value,
    userId = dao.userId,
    title = dao.title,
    description = dao.description,
    created = dao.created,
    updated = dao.updated
)

internal fun BlogDAO.toBlogModel(): Blog {
    return daoToModel(this)
}