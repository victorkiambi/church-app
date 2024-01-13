package com.app.church.domain.repository

import androidx.paging.PagingData
import com.app.church.domain.model.posts.Content
import com.app.church.domain.model.posts.Data
import kotlinx.coroutines.flow.Flow

interface UserPostRepository {

    suspend fun getUserPosts(): Flow<PagingData<Data>>
}