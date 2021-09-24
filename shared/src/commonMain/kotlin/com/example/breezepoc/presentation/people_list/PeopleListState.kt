package com.example.breezepoc.presentation.people_list

import com.example.breezepoc.domain.model.PeopleList.Person

data class PeopleListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val people: List<Person> = listOf(),
){
    // Need secondary zero-arg constructor to init with no args in SwiftUI

    constructor(): this(
        isLoading = false,
        page = 1,
        query = "",
        people = listOf()
    )
}
