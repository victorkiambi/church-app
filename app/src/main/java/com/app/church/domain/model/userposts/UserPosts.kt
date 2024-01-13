package com.app.church.domain.model.userposts

data class UserPosts(
    val content: List<Content>,
    val isEmpty: Boolean,
    val isFirst: Boolean,
    val isLast: Boolean,
    val page: Int,
    val size: Int,
    val totalElements: Int,
    val totalPages: Int
)