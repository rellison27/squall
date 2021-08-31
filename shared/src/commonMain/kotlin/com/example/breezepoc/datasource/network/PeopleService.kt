package com.example.breezepoc.datasource.network

import com.example.breezepoc.domain.model.Person

interface PeopleService {

    suspend fun getPeople():List<Person>

    // TODO: add a model for a single person api call
    suspend fun getPerson(
        id: Long
    ): Person
}