package com.example.breezepoc.datasource.network.cache

import com.example.breezepoc.domain.model.PeopleList.Person
import com.example.breezepoc.domain.model.PeopleList.Phone
import com.example.breezepoc.domain.model.Person.SinglePerson

interface PeopleCache {

    fun insert(person: Person)

    fun insert(people: List<Person>)

    fun insertPhone(person: Person)

    // may implement a search feature
    // fun search(query: String, page: Int)

    // may take page param in the future for ininite load but will not for now
    // because prod doesn't have many people
    fun getAll(): List<Person>

    @Throws(NullPointerException::class)
    fun get(personId: Int): SinglePerson?
}