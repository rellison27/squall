package com.example.breezepoc.datasource.network.model

import com.example.breezepoc.domain.model.Name
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NameDto(

    @SerialName("first")
    var first: String? = null,

    @SerialName("last")
    var last: String? = null,

    @SerialName("middle")
    var middle: String? = null,

    @SerialName("maiden")
    var maiden: String? = null,

    @SerialName("nick")
    var nick: String? = null,
)