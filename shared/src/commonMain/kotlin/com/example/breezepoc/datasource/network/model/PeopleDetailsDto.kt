package com.example.breezepoc.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PeopleDetailsDto (

    @SerialName("name")
    val name: NameDto? = null,

    @SerialName("phone")
    val phone: PeoplePhoneDto? = null,

    @SerialName("email")
    val email: String? = null,

    @SerialName("archived")
    val archived: Int,

    @SerialName("profile_picture")
    val profilePicture: String?,
)