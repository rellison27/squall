package com.example.breezepoc.interactors.people_list

import com.example.breezepoc.datasource.network.PeopleService
import com.example.breezepoc.domain.model.PeopleList.Person
import com.example.breezepoc.domain.model.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PopulatePeopleList(
    private val peopleService: PeopleService
) {
    fun execute(): Flow<DataState<List<Person>>> = flow {
        emit(DataState.loading())
        // emit people
        try {
            val people = peopleService.getPeople()
            emit(DataState.data(message = null,data = people))
        } catch (e: Exception) {
            emit(DataState.error<List<Person>>(message = e.message ?: "Unknown Error"))
        }
    }
}