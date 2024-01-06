# RetrofitBasic

このプロジェクトでは、MVVM (Model-View-ViewModel) パターンを採用したRetrofitを使用してHTTP GETリクエストを行うAndroidアプリを作成します。

📁ディレクトリ構成:
```
app/src/main/java
└── com
    └── junichi
        └── retrofitbasic
            ├── MainActivity.kt
            ├── model
            │   └── Todo.kt
            ├── repository
            │   ├── ApiService.kt
            │   └── RetrofitInstance.kt
            ├── ui
            │   └── theme
            │       ├── Color.kt
            │       ├── Theme.kt
            │       └── Type.kt
            └── view_model
                ├── MainViewModel.kt
                └── PostList.kt
```

## 手順

1. **依存関係の追加**: `build.gradle`ファイルにRetrofitとGsonの依存関係を追加します。

```groovy
dependencies {
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
}
```

2. APIインターフェースの作成: Retrofitを使用してAPIリクエストを行うためのインターフェースを作成します。

```kt
interface ApiService {
    @GET("endpoint")
    fun getData(): Call<List<Data>>
}
```
3. Retrofitインスタンスの作成: Retrofitインスタンスを作成し、APIインターフェースを初期化します。
```kt
val retrofit = Retrofit.Builder()
    .baseUrl("https://api.example.com")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService = retrofit.create(ApiService::class.java)
```
4. リポジトリの作成: APIリクエストを行うためのリポジトリクラスを作成します。
```kt
class Repository {
    fun getData(): LiveData<List<Data>> {
        val data = MutableLiveData<List<Data>>()

        apiService.getData().enqueue(object : Callback<List<Data>> {
            override fun onResponse(call: Call<List<Data>>, response: Response<List<Data>>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<List<Data>>, t: Throwable) {
                // Handle failure
            }
        })

        return data
    }
}
```
5. ViewModelの作成: リポジトリを使用してデータを取得するViewModelを作成します。
```kt
class MainViewModel : ViewModel() {
    private val repository = Repository()

    fun getData(): LiveData<List<Data>> {
        return repository.getData()
    }
}
```
6. Viewの更新: ViewModelを使用してViewを更新します。
```kt
val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

viewModel.getData().observe(this, Observer { data ->
    // Update the UI
})
```

以上が、MVVMパターンを採用したRetrofitを使用してHTTP GETリクエストを行うAndroidアプリの基本的な手順です。
