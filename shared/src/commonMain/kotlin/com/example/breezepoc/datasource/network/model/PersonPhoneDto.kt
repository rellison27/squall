package com.example.breezepoc.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonPhoneDto(

    @SerialName("home")
    var home: NumberDto?,

    @SerialName("work")
    var work: NumberDto?,

    @SerialName("mobile")
    var mobile: NumberDto?
)

@Serializable
data class NumberDto(

    @SerialName("number")
    var number: String?
)
