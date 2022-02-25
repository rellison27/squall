package com.example.breezepoc.domain.model.Login

data class Token(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int
)