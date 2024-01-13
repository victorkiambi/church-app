package com.app.church.domain.model.posts

data class Data(
    val category_id: Int,
    val comments: List<Comment>,
    val content: String,
    val created_at: String,
    val deleted_at: Any,
    val id: Int,
    val title: String,
    val updated_at: String,
    val user: User,
    val user_id: Int
)