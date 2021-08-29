package com.example.breezepoc.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhoneDto(

    @SerialName("home")
    var home: String?,

    @SerialName("work")
    var work: String?,

    @SerialName("mobile")
    var mobile: String?,
)
