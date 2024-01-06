package com.junichi.retrofitbasic.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
シングルトンの Retrofit インスタンス
このインスタンスは実際の API 呼び出しを処理する
*/
object RetrofitInstance {
    private
    const val BASE_URL = "https://jsonplaceholder.typicode.com/"// jsonplaceholderのTODO APIのベースURL
    val api: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}