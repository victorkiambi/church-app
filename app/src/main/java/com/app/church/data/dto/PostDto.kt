package com.app.church.data.dto

import com.app.church.domain.model.posts.User

data class PostDto(
    val content: String,
    val createdAt: String,
    val deletedAt: Any,
    val id: Int,
    val title: String,
    val updatedAt: Any,
    val user: User,
    val userId: Int
)
