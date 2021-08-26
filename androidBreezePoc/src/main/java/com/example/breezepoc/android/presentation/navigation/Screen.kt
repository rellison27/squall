package com.example.breezepoc.android.presentation.navigation

sealed class Screen (
    val route: String
        ) {
    object PeopleList: Screen("peopleList")
    object personDetail: Screen("personDetail")
}