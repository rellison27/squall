package com.example.breezepoc.interactors.person_detail

import com.example.breezepoc.datasource.network.PeopleService
import com.example.breezepoc.domain.model.Person.SinglePerson
import com.example.breezepoc.domain.model.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PopulatePersonDetail(
    private val peopleService: PeopleService // will come from cache later
) {
    fun execute(
        personId: Int,
    ): Flow<DataState<SinglePerson>> = flow {
        emit(DataState.loading())
        try {

            val person = peopleService.getPerson(personId)

            emit(DataState.data(message = null, data = person))
        } catch (e: Exception) {
            emit(DataState.error<SinglePerson>(message = e.message ?: "Unknown Error"))
        }
    }
}