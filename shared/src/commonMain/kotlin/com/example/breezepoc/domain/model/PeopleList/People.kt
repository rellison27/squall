package com.example.breezepoc.domain.model.PeopleList


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

data class PeopleDetails (
    val name: Name?,
    val phone: Phone?,
    val email: String?,
    val archived: Int,
    val profilePicture: String?
)
data class Person(
    val id: Long?,
    val personDetails: PeopleDetails?
)

data class People(
    val data: List<Person>,
)


