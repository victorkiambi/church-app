package com.app.church.domain.model.posts

data class Comment(
    val comments: String,
    val id: Int,
    val post_id: Int
)