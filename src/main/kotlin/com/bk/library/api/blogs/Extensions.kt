package com.bk.library.api.blogs

import com.bk.library.api.blogs.model.Blog

fun Blog.isEmpty() = this.title.isEmpty() && this.description.isEmpty()