package com.app.church.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.church.domain.model.posts.Data
import com.app.church.domain.model.posts.User
import com.app.church.ui.theme.ChurchAppTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PostCard(post: Data) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = post.user.username,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = formatDateTime(post.created_at),
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = post.title,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = post.content,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ButtonGroup("Like", "Comment", "Share")
            }
        }
    }
}

@Composable
fun ButtonGroup(vararg labels: String) {
    labels.forEach { label ->
        Button(onClick = { /* TODO: Add button functionality */ }) {
            Text(label)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateTime(dateTimeStr: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
    val dateTime = LocalDateTime.parse(dateTimeStr, formatter)
    return dateTime.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showBackground = true)
fun PostCardPreview() {
    val samplePost = Data(
        title = "Another comment",
        content = "Lorem ipsum ipsum lorem",
        created_at = "2023-11-20T05:03:12.054177",
        user = User(
            username = "victor254",
            id = 1
        ),
        id = 1,
        updated_at = "2023-11-20T05:03:12.054177",
        user_id = 1,
        category_id = 1,
        comments = listOf(),
        deleted_at = ""
    )
    ChurchAppTheme {
        PostCard(post = samplePost)
    }
}