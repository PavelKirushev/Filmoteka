package com.example.auth.data.network

//object FilmApiProvider {
//    val baseUrl = "https://kinopoiskapiunofficial.tech/"
//
//    val filmApi: FilmApi by lazy {
//        Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(FilmApi::class.java)
//    }
//}
//
//object FilmApiProvider {
//    val filmApi: FilmApi by lazy {
//        val client = HttpClient {
//            install(ContentNegotiation) {
//                json(json = Json {
//                    ignoreUnknownKeys = true
//                })
//            }
//        }
//        FilmApi(client)
//    }
//}

expect class FilmApiProvider() {
    fun create(apiKey: String): FilmApi
}