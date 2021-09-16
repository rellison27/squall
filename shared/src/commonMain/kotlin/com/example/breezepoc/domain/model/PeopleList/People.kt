package com.example.breezepoc.domain.model.PeopleList

data class Email (
    val address: String?,
    val private: Boolean?,
    val exclude: Boolean?
)

data class Address (
    val street: String?,
    val city: String?,
    val state: String?,
    val zip: String?,
    val longitude: String?,
    val latitude: String?,
    val private: Boolean?
)

data class Name(
    val first: String?,
    val last: String?,
    val middle: String?,
    val maiden: String?,
    val nick: String?,
)

data class PhoneNumber(
    val number: String?,
    val private: Boolean?
)

data class Phone(
    val home: PhoneNumber?,
    val work: PhoneNumber?,
    val mobile: PhoneNumber?,

)

data class Person(
    val id: Long,
    val archived: Boolean?,
    val birthdate: Long?,
    val address: Address?,
    val email: Email?,
    val phone: Phone?,
    val name: Name?,
    val profilePicture: String?,
)

data class People(
    val data: List<Person>,
)


