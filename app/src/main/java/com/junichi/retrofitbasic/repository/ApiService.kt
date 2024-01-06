package com.junichi.retrofitbasic.repository

import com.junichi.retrofitbasic.model.Todo
import retrofit2.http.GET
// BASE URLの後に続くエンドポイントを定義する。今回だと`todos`を@GETの引数に指定する
interface ApiService {
    @GET("todos")
    // kotlinのsuspend関数を使って非同期処理を行う
    suspend fun getPosts(): List <Todo>
}