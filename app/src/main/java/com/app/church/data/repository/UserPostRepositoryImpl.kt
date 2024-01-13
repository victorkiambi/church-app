package com.app.church.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.church.data.ChurchApi
import com.app.church.domain.model.posts.Content
import com.app.church.domain.model.posts.Data
import com.app.church.domain.repository.UserPostRepository
import kotlinx.coroutines.flow.Flow

class UserPostRepositoryImpl(
    private val api: ChurchApi
) : UserPostRepository {
    override suspend fun getUserPosts(): Flow<PagingData<Data>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = { UserPostsPagingSource(api)
            }
        ).flow
    }
}