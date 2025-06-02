package com.example.auth.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Token(
    val token: String
)
