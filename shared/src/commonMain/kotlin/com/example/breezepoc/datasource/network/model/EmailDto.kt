package com.example.breezepoc.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmailDto(

    @SerialName("address")
    val address: String?,

    @SerialName("private")
    val private: Boolean?,

    @SerialName("exclude")
    val exclude: Boolean?
)
