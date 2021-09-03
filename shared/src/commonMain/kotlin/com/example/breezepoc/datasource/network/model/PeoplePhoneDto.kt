package com.example.breezepoc.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PeoplePhoneDto(

    @SerialName("home")
    val home: String?,

    @SerialName("work")
    val work: String?,

    @SerialName("mobile")
    val mobile: String?,
)
