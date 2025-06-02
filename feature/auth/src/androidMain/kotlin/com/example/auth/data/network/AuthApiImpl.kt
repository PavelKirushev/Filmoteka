package com.example.auth.data.network

import com.example.auth.domain.models.Token
import com.example.auth.domain.models.User
import com.example.auth.domain.models.UserInfo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class AuthApiImpl() : AuthApi {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    override suspend fun getUser(token: Token): User {
        return client.get {
            url("http://10.0.2.2:8080/me")
            header("Authorization", "Bearer ${token.token}")
        }.body()
    }

    override suspend fun login(userInfo: UserInfo): Token {
        return client.post {
            url("http://10.0.2.2:8080/login")
            contentType(ContentType.Application.Json)
            setBody(userInfo)
        }.body()
    }

    override suspend fun setUser(user: User) {
        return client.post {
            url("http://10.0.2.2:8080/users")
            contentType(ContentType.Application.Json)
            setBody(user)
        }.body()
    }
}