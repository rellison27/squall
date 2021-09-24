package com.example.breezepoc.android.presentation.people_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breezepoc.domain.model.PeopleList.Person
import com.example.breezepoc.interactors.people_list.PopulatePeopleList
import com.example.breezepoc.presentation.people_list.PeopleListEvents
import com.example.breezepoc.presentation.people_list.PeopleListState
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
        onTriggerEvent(PeopleListEvents.LoadPeople)
    }

    fun onTriggerEvent(event: PeopleListEvents){
        when(event){
            PeopleListEvents.LoadPeople -> {
                loadPeople()
            }
            PeopleListEvents.NextPage -> {
                nextPage()
            }
            else -> {
                handleError("Invalid Event")
            }
        }
    }

    private fun nextPage(){
        state.value = state.value.copy(page = state.value.page + 1)
        loadPeople()
    }

    private fun loadPeople(){
        // TODO(
        //  I have no idea how pagination works on this API but we would put the page number or
        //  some state in side of this execute fun below. it also needs to be added to the model
        //  potentially
        //  )
        populatePeopleList.execute().collectCommon(viewModelScope) { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)
            println("PeopleListVM: ${dataState.isLoading}")

            dataState.data?.let { people ->
                appendPeople(people)
                println("PeopleListVM: person: ${people}")
            }

            dataState.message?.let { message ->
                println("PeopleListVM: error: ${message}")
            }
        }
    }

    private fun appendPeople(people: List<Person>){
        val curr = ArrayList(state.value.people)
        curr.addAll(people)
        state.value = state.value.copy(people = curr)
    }

    private fun handleError(errorMessage: String){
        // TODO("Handle errors")
    }
}