package com.example.breezepoc.interactors.people_list

import com.example.breezepoc.datasource.network.PeopleService
import com.example.breezepoc.datasource.network.cache.PeopleCache
import com.example.breezepoc.domain.model.PeopleList.Person
import com.example.breezepoc.domain.model.util.CommonFlow
import com.example.breezepoc.domain.model.util.DataState
import com.example.breezepoc.domain.model.util.asCommonFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PopulatePeopleList(
    private val peopleService: PeopleService,
    private val peopleCache: PeopleCache
) {
    fun execute(): CommonFlow<DataState<List<Person>>> = flow {
        emit(DataState.loading())
        // emit people
        try {
            val people = peopleService.getPeople()
            // delay 500ms so we can see loading
            delay(5000)

            // insert people into cache
           peopleCache.insert(people)

            // query cache
            val cacheResults = peopleCache.getAll()
            emit(DataState.data(message = null, data = cacheResults))

        } catch (e: Exception) {
            val existingCache = peopleCache.getAll()

            // delay 500ms so we can see loading offline too
            delay(5000)
            if(existingCache !== null) emit(DataState.data(message = null, data = existingCache))
            emit(DataState.error<List<Person>>(message = e.message ?: "Unknown Error"))
        }
    }.asCommonFlow()
}