package com.junichi.retrofitbasic.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// ViewModelからデータを受け取って表示する`UI`のコンポーネント
@Composable
fun PostList(viewModel: MainViewModel) {
    // ViewModelからデータを受け取る
    val posts = viewModel.posts.value
    // 受け取ったデータを表示する
    LazyColumn {
        items(posts) { post ->// lambda式でデータを受け取る
            // Cardコンポーネントを使ってデータを表示する
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 400.dp, height = 100.dp)
                      .padding(10.dp)
            ) {
                Row {
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(text = post.id.toString())
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(text = post.title)
                }
            }
        }
    }
    // データを取得する関数を呼び出す
    DisposableEffect(Unit) {
        // onActiveで画面が表示された時にデータを取得する
        viewModel.getPosts()
        // onDisposeで画面が閉じられた時にデータを破棄する
        onDispose {
            // データを破棄する
            viewModel.posts.value = emptyList()
        }
    }
}