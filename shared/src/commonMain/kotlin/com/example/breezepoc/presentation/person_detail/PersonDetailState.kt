package com.example.breezepoc.presentation.person_detail

import com.example.breezepoc.domain.model.Person.SinglePerson

data class PersonDetailState(
    val isLoading: Boolean = false,
    val person: SinglePerson? = null
    // val queue: Queue<GenericMessageInfo> = Queue(mutableListOf()), // messages to be displayed in ui
) {

    // Need secondary constructor to initialize with no args in SwiftUI
    constructor(): this(
        isLoading = false,
        person = null
        // queue = Queue(mutableListOf()),
    )
}