package com.example.breezepoc.domain.model.Person

import com.example.breezepoc.domain.model.PeopleList.Address
import com.example.breezepoc.domain.model.PeopleList.Name
import com.example.breezepoc.domain.model.PeopleList.Phone

data class Email (
    val address: String?,

)

data class SinglePersonDetails(
    val name: Name?,
    val address: Address?,
    val profilePicture: String?,
    val birthdate: String?,
    val phone: Phone?,
    val email: Email?,
    val archived: Boolean?
)

data class SinglePerson(
    val id: Long?,
    val personDetails: SinglePersonDetails?
)

data class SinglePersonResponse(
    val data: SinglePerson?
)