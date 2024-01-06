package com.junichi.retrofitbasic.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junichi.retrofitbasic.model.Todo
import com.junichi.retrofitbasic.repository.RetrofitInstance
import kotlinx.coroutines.launch

// データクラスとレポジトリ、View側との間でデータをやり取りするViewModel
class MainViewModel: ViewModel() {
    // レポジトリのインスタンスを取得
    private val apiService = RetrofitInstance.api
    // レポジトリから取得したデータを格納する変数
    val posts: MutableState<List<Todo>> = mutableStateOf(emptyList())
    // レポジトリからデータを取得する関数
    fun getPosts() {
        // viewModelScopeを使って非同期処理を行う
        viewModelScope.launch {
            try {
                // 例外処理が起きなかったらpostsにデータを格納する
                val response = apiService.getPosts()
                if (response.isNotEmpty()) {
                    posts.value = response
                }
                // エラーが起きたら例外を投げる
            } catch (e: Exception) {
                throw Exception(e)
            }
        }
    }
}