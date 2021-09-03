package com.example.breezepoc.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonPhoneDto(

    @SerialName("home")
    val home: NumberDto?,

    @SerialName("work")
    val work: NumberDto?,

    @SerialName("mobile")
    val mobile: NumberDto?
)

@Serializable
data class NumberDto(

    @SerialName("number")
    val number: String?
)
