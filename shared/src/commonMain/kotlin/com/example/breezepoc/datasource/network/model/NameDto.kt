package com.example.breezepoc.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NameDto(

    @SerialName("first")
    var first: String?,

    @SerialName("last")
    var last: String?,

    @SerialName("middle")
    var middle: String?,

    @SerialName("maiden")
    var maiden: String?,

    @SerialName("nick")
    var nick: String?,
)
