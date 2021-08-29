package com.example.breezepoc.domain.model

data class Name(
    val first: String?,
    val last: String?,
    val middle: String?,
    val maiden: String?,
    val nick: String?,
)

data class Phone(
    val home: String?,
    val work: String?,
    val mobile: String?,

)

data class PersonDetails (
    val name: Name?,
    val phone: Phone?,
    val email: String?,
    val archived: Int,
    val picture: String?
)

data class Person(
    val id: Long,
    val personDetails: PersonDetails
)
