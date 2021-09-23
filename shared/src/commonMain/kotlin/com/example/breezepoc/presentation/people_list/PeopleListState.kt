package com.example.breezepoc.presentation.people_list

import com.example.breezepoc.domain.model.PeopleList.Person

data class PeopleListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val people: List<Person> = listOf(),
)
