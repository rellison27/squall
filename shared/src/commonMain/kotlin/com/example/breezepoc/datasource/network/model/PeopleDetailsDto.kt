package com.example.breezepoc.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PeopleDetailsDto (

    @SerialName("name")
    var name: NameDto? = null,

    @SerialName("phone")
    var phone: PeoplePhoneDto? = null,

    @SerialName("email")
    var email: String? = null,

    @SerialName("archived")
    var archived: Int,

    @SerialName("profile_picture")
    var profilePicture: String?,
)