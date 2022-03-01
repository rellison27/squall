package com.example.breezepoc.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhoneDto(

    @SerialName("home")
    val home: NumberDto? = null,

    @SerialName("work")
    val work: NumberDto? = null,

    @SerialName("mobile")
    val mobile: NumberDto? = null
)

@Serializable
data class NumberDto(

    @SerialName("number")
    val number: String? = null,

    @SerialName("private")
    val private: Boolean? = null
)
