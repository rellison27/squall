package com.example.breezepoc.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonDetailsDto(

    @SerialName("name")
    var name: NameDto?,

    @SerialName("profile_picture")
    var profilePicture: String?,

    @SerialName("email")
    var email: PersonEmailDto?,

    @SerialName("archived")
    var archived: Boolean?,

    @SerialName("address")
    val address: AddressDto?,

    @SerialName("birthdate")
    val birthdate: String?,

    @SerialName("phone")
    val phone: PersonPhoneDto?
)