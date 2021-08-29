package com.example.breezepoc.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonSearchPhoneResponseDto(

    @SerialName("mobile")
    var mobile: MobilePhoneDto
)

@Serializable
data class MobilePhoneDto(

    @SerialName("number")
    var number: String?,

    @SerialName("private")
    var private: Boolean?,

    @SerialName("exclude")
    var exclude: Boolean?
)
