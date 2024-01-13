package com.app.church.screens.posts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.app.church.components.PostCard
import com.app.church.domain.model.posts.Content
import com.app.church.domain.model.posts.Data
import com.app.church.ui.theme.Primary

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PostsScreen(
    viewModel: PostsViewModel = hiltViewModel()
){
    val postItems: LazyPagingItems<Data> = viewModel.postsState.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
    ) {
        items(postItems.itemCount) { index ->
            postItems[index]?.let { post ->
                PostCard(post = post)
            }}
            postItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(Alignment.CenterVertically)
                            )
                        }
                    }
                    loadState.refresh is LoadState.Error -> {
                        item {
                            Text(
                                text = "Error",
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                fontSize = 25.sp
                            )
                        }
                    }
                    loadState.append is LoadState.Loading -> {
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(Alignment.CenterVertically)
                            )
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        item {
                            Text(
                                text = "Error",
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                fontSize = 25.sp
                            )
                        }
                    }

            }
        }
    }
}
