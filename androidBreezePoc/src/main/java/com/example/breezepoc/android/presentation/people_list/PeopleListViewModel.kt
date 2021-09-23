package com.example.breezepoc.android.presentation.people_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breezepoc.domain.model.PeopleList.Person
import com.example.breezepoc.domain.model.Person.SinglePerson
import com.example.breezepoc.interactors.people_list.PopulatePeopleList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PeopleListViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle, // don't need for this VM
    private val populatePeopleList: PopulatePeopleList
): ViewModel (){

    val state: MutableState<PeopleListState> = mutableStateOf(PeopleListState())

    init {
        loadPeople()
    }

    private fun loadPeople(){
        populatePeopleList.execute().onEach { dataState ->
            println("PeopleListVM: ${dataState.isLoading}")

            dataState.data?.let { people ->
                appendPeople(people)
                println("PeopleListVM: person: ${people}")
            }

            dataState.message?.let { message ->
                println("PeopleListVM: error: ${message}")
            }
        }.launchIn(viewModelScope)
    }

    private fun appendPeople(people: List<Person>){
        val curr = ArrayList(state.value.people)
        curr.addAll(people)
        state.value = state.value.copy(people = curr)
    }
}