package com.example.breezepoc.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonDetailsDto (

    @SerialName("name")
    var name: NameDto,

    @SerialName("phone")
    var phone: PhoneDto,

    @SerialName("email")
    var email: String?,

    @SerialName("archived")
    var archived: Int,

    @SerialName("picture")
    var picture: String?,
)