package com.example.breezepoc.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressDto(

    @SerialName("street")
    val street: String?,

    @SerialName("city")
    val city: String?,

    @SerialName("state")
    val state: String?,

    @SerialName("zip")
    val zip: String?,

    @SerialName("longitude")
    val longitude: String?,

    @SerialName("latitude")
    val latitude: String?,

    @SerialName("private")
    val private: Boolean?,

)
