package com.app.church.data

import com.app.church.domain.model.posts.Posts
import retrofit2.http.GET

interface ChurchApi {

    @GET("posts")
    suspend fun getPosts(): Posts

    @GET("user-posts")
    suspend fun getUserPosts(): Posts
}