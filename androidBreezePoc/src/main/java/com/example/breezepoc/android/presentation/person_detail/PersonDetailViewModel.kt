package com.example.breezepoc.android.presentation.person_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breezepoc.domain.model.Person.SinglePerson
import com.example.breezepoc.interactors.person_detail.PopulatePersonDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PersonDetailViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val populatePersonDetail: PopulatePersonDetail
) : ViewModel() {

    val person: MutableState<SinglePerson?> = mutableStateOf(null)

    init {
        savedStateHandle.get<Int>("personId")?.let { personId ->
            populatePersonDetail(personId = personId)
        }
    }

    private fun populatePersonDetail(personId: Int) {
        populatePersonDetail.execute(personId = personId).onEach { dataState ->
            println("PersonDetailVM: loading: ${dataState.isLoading}")

            dataState.data?.let { person ->
                println("PersonDetailVM person ${person}")
                this.person.value = person
            }

            dataState.message?.let { message ->
                println("PersonDetailVM: error: ${message}")
            }
        }.launchIn(viewModelScope)
    }
}