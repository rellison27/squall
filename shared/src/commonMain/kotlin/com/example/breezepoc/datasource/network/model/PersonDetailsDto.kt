package com.example.breezepoc.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonDetailsDto(

    @SerialName("name")
    val name: NameDto?,

    @SerialName("profile_picture")
    val profilePicture: String?,

    @SerialName("email")
    val email: EmailDto?,

    @SerialName("archived")
    val archived: Boolean?,

    @SerialName("address")
    val address: AddressDto?,

    @SerialName("birthdate")
    val birthdate: String?,

    @SerialName("phone")
    val phone: PhoneDto?
)