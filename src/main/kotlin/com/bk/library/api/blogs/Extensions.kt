package com.bk.library.api.blogs

import com.bk.library.api.blogs.model.BlogRequest

internal fun BlogRequest.isEmpty() = this.blogData.title.isEmpty() && this.blogData.description.isEmpty()