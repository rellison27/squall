package com.example.breezepoc.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonDto(

    @SerialName("id")
    val id: Long?,

    @SerialName("person_details")
    val personDetails: PersonDetailsDto?

)
