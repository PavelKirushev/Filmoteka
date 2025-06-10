package com.example.auth.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo (
    val login: String,
    val password: String
)