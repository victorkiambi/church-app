package com.app.church.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.app.church.data.ChurchApi
import com.app.church.domain.model.posts.Content
import com.app.church.domain.model.posts.Data
import com.app.church.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class PostRepositoryImpl(
    private val api: ChurchApi
) : PostRepository {
    override suspend fun getPosts(): Flow<PagingData<Data>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = { PostsPagingSource(api)
            }
        ).flow
    }
}