package com.app.church.domain.model.userposts

data class Content(
    val comments: List<Any>,
    val content: String,
    val createdAt: String,
    val deletedAt: Any?,
    val id: Int,
    val title: String,
    val updatedAt: Any,
    val user: User,
    val userId: Int
)