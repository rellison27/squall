package com.example.breezepoc.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PeopleDto(

    @SerialName("id")
    val id: Long,

    @SerialName("archived")
    val archived: Boolean?,

    @SerialName("birthdate")
    val birthdate: Long?,

    @SerialName("address")
    val address: AddressDto?,

    @SerialName("email")
    val email: EmailDto? = null,

    @SerialName("phone")
    val phone: PhoneDto? = null,

    @SerialName("name")
    val name: NameDto? = null,

    @SerialName("profile_photo")
    val profilePicture: String?,
)
