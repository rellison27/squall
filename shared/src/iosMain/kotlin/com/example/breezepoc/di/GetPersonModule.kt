package com.example.breezepoc.di

import com.example.breezepoc.datasource.cache.GetPersonById
import com.example.breezepoc.interactors.person_detail.PopulatePersonDetail

class GetPersonModule(
    private val cacheModule: CacheModule
) {
    val populatePersonDetail: PopulatePersonDetail by lazy {
        PopulatePersonDetail(peopleCache = cacheModule.peopleCache)
    }
}