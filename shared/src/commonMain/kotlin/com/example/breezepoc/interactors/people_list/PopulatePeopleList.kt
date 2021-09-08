package com.example.breezepoc.interactors.people_list

import com.example.breezepoc.datasource.network.PeopleService
import com.example.breezepoc.datasource.network.cache.PeopleCache
import com.example.breezepoc.domain.model.PeopleList.Person
import com.example.breezepoc.domain.model.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PopulatePeopleList(
    private val peopleService: PeopleService,
    private val peopleCache: PeopleCache
) {
    fun execute(): Flow<DataState<List<Person>>> = flow {
        emit(DataState.loading())
        // emit people
        try {
            val people = peopleService.getPeople()

            // insert people into cache
            peopleCache.insert(people)

            // query cache
            val cacheResults = peopleCache.getAll()
            emit(DataState.data(message = null, data = cacheResults))

        } catch (e: Exception) {
            emit(DataState.error<List<Person>>(message = e.message ?: "Unknown Error"))
        }
    }
}