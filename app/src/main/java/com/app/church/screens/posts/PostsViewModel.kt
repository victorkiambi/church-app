package com.app.church.screens.posts

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.app.church.domain.model.posts.Content
import com.app.church.domain.model.posts.Data
import com.app.church.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {

    private val _postsState: MutableStateFlow<PagingData<Data>> = MutableStateFlow(value = PagingData.empty())
    val postsState: MutableStateFlow<PagingData<Data>> get() = _postsState

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            repository.getPosts().collectLatest {
                try {
                    _postsState.value = it
                } catch (e: Exception) {
                    Log.e("PostsViewModel", "getPosts: ${e.message}")
            }
            }
        }
    }
}