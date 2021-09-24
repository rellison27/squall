package com.example.breezepoc.presentation.people_list

sealed class PeopleListEvents {
    object LoadPeople: PeopleListEvents()

    object NextPage: PeopleListEvents()

}