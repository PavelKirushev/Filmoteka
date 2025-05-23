package com.example.auth.domain.models

data class User(
    val login: String,
    val email: String,
    val password: String,
    val age: Int
)