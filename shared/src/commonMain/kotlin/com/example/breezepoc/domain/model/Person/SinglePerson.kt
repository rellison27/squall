package com.example.breezepoc.domain.model.Person

import com.example.breezepoc.domain.model.PeopleList.Name

data class Email (
    val address: String?
)

data class PhoneNumber(
    val number: String?
)

data class SinglePhone(
    val home: PhoneNumber?,
    val work: PhoneNumber?,
    val mobile: PhoneNumber?
)

data class Address(
    val StreetAddress: String?,
    val city: String?,
    val state: String?,
    val zip: String?
)

data class SinglePersonDetails(
    val name: Name?,
    val address: Address?,
    val profilePicture: String?,
    val birthdate: String?,
    val phone: SinglePhone?,
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