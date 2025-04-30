package com.example.auth.data

import com.example.auth.data.models.Token
import com.example.auth.domain.User
import com.example.auth.data.models.UserInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @GET("me")
    suspend fun getUser(@Header("Authorization") token: String): Response<User>

    @POST("login")
    suspend fun login(@Body userInfo: UserInfo): Response<Token>
}