package com.example.breezepoc.di

import com.example.breezepoc.interactors.people_list.PopulatePeopleList

class PopulatePeopleListModule(
    val networkModule: NetworkModule,
    val cacheModule: CacheModule
) {

    val populatePeopleList: PopulatePeopleList by lazy {
        PopulatePeopleList(
            peopleService = networkModule.peopleService,
            peopleCache = cacheModule.peopleCache
        )
    }
}