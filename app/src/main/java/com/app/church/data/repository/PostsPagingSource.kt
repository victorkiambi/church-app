package com.app.church.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.church.data.ChurchApi
import com.app.church.domain.model.posts.Content
import com.app.church.domain.model.posts.Data
import javax.inject.Inject

class PostsPagingSource @Inject constructor(
    private val api: ChurchApi
) : PagingSource<Int, Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val pageNumber = params.key ?: 0
            val response = api.getPosts()

            val nextPageNumber = if (pageNumber < response.totalPages - 1) pageNumber + 1 else null

            LoadResult.Page(
                data = response.content.data,
                prevKey = if (pageNumber == 0) null else pageNumber - 1,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            Log.e("PostsPagingSource", "load: ${e.message}")
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition
    }
}