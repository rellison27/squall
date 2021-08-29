package com.example.breezepoc.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonSearchResponseDto(

    @SerialName("id")
    var id: Long,

    @SerialName("name")
    var name: NameDto,

    @SerialName("profile_photo")
    var profilePhoto: String?,

    @SerialName("phone")
    var phone: PersonSearchPhoneResponseDto?,
)
