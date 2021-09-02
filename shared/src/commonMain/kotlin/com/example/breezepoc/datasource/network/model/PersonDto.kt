package com.example.breezepoc.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonDto(

    @SerialName("id")
    var id: Long?,

    @SerialName("person_details")
    var personDetails: PersonDetailsDto?

)
