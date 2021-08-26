package com.example.breezepoc.android.presentation.person_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.breezepoc.android.di.Dummy
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonDetailViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle,
    private val dummy: Dummy
) : ViewModel() {

    val personId: MutableState<Int?> = mutableStateOf(null)

    init {
        savedStateHandle.get<Int>("personId")?.let { personId ->
            this.personId.value = personId
        }
        println("PersonDetailViewModel: ${dummy.description()}")
    }
}