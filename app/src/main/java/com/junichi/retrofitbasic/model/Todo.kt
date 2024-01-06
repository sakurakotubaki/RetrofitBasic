package com.junichi.retrofitbasic.model
/* jsonplaceholderのTODO APIのレスポンスのデータクラス
JSONデータ型に合わせてクラスのメンバー変数を定義する
 */
data class Todo(
    val id: Int,
    val userId: Int,
    val title: String,
    var completed: Boolean
)
