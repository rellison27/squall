package com.example.breezepoc.domain.model.Login

import kotlinx.serialization.Serializable

@Serializable
data class Auth(
    val username: String,
    val password: String,
    val subdomain: String
)