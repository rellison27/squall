package com.example.breezepoc.datasource.network

import com.example.breezepoc.domain.model.PeopleList.Person
import com.example.breezepoc.domain.model.Person.SinglePerson

interface PeopleService {

    suspend fun getPeople():List<Person>

    suspend fun getPerson(
        id: Int
    ): SinglePerson
}