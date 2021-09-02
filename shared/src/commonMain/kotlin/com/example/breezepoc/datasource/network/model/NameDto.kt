package com.example.breezepoc.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NameDto(

    @SerialName("first")
    val first: String?,

    @SerialName("last")
    val last: String?,

    @SerialName("middle")
    val middle: String?,

    @SerialName("maiden")
    val maiden: String?,

    @SerialName("nick")
    val nick: String?,
)