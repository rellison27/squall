package com.example.breezepoc.android.presentation.person_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breezepoc.datasource.network.PeopleService
import com.example.breezepoc.domain.model.Person.SinglePerson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PersonDetailViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val peopleService: PeopleService
) : ViewModel() {

    val person: MutableState<SinglePerson?> = mutableStateOf(null)

    init {
        try {
            println("Saved State: ${savedStateHandle.get<Int>("personId")}")
            savedStateHandle.get<Int>("personId")?.let { personId ->
                viewModelScope.launch {
                    person.value = peopleService.getPerson(personId)
                    println("PersonId: ")
                    println("KtorTest: ${person?.value?.id}")
                    println("KtorTest: ${person?.value?.personDetails?.name?.first}")
                    println("KtorTest: ${person?.value?.personDetails?.name?.last}")
                    println("KtorTest: ${person?.value?.personDetails?.phone?.mobile?.number}")
                    println("KtorTest: ${person?.value?.personDetails?.email?.address}")
                    println("KtorTest: ${person?.value?.personDetails?.profilePicture}")
                }
            }
        } catch (e: Exception) {
            println("PeopleService failed $e")
        }

    }
}