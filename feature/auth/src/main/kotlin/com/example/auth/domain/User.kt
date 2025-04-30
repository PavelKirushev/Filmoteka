package com.example.auth.domain

data class User(
    val login: String,
    val email: String,
    val password: String,
    val age: Int
)