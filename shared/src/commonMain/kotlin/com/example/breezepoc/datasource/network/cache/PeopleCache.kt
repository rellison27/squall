package com.example.breezepoc.datasource.network.cache

import com.example.breezepoc.domain.model.Person.SinglePerson

interface PeopleCache {

    fun insert(person: SinglePerson)

    fun insert(person: List<SinglePerson>)

    // may implement a search feature
    // fun search(query: String, page: Int)

    // may take page param in the future for ininite load but will not for now
    // because prod doesn't have many people
    fun getAll(): List<SinglePerson>

    @Throws(NullPointerException::class)
    fun get(personId: Int): SinglePerson?
}