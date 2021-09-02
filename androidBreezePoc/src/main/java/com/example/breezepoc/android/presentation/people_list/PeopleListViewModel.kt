package com.example.breezepoc.android.presentation.people_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.breezepoc.datasource.network.PeopleService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeopleListViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle, // don't need for this VM
    private val peopleService: PeopleService
): ViewModel (){
}