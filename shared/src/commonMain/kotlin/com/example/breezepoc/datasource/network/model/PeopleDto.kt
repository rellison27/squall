package com.example.breezepoc.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PeopleDto(

    @SerialName("id")
    val id: Long?,

    @SerialName("person_details")
    val personDetails: PeopleDetailsDto?
)
