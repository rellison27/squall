package com.example.breezepoc.android.presentation.people_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    init {
        loadPeople()
    }

    private fun loadPeople(){
        populatePeopleList.execute().onEach { dataState ->
            println("PeopleListVM: ${dataState.isLoading}")

            dataState.data?.let { person ->
                println("PeopleListVM: person: ${person}")
            }

            dataState.message?.let { message ->
                println("PeopleListVM: error: ${message}")
            }
        }.launchIn(viewModelScope)
    }
}