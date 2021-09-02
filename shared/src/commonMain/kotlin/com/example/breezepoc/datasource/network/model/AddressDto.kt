package com.example.breezepoc.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddressDto(

    @SerialName("street_address")
    val streetAddress: String?,

    @SerialName("city")
    val city: String?,

    @SerialName("state")
    val state: String?,

    @SerialName("zip")
    val zip: String?,

)
