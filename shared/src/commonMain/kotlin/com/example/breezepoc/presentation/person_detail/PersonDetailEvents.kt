package com.example.breezepoc.presentation.person_detail

import com.example.breezepoc.interactors.person_detail.PopulatePersonDetail

sealed class PersonDetailEvents {

    data class PopulatePersonDetail(val personId: Int): PersonDetailEvents()

}