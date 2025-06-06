package com.example.auth.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val login: String,
    val email: String,
    val password: String,
    val age: Int?
)