package com.example.breezepoc.di

import com.example.breezepoc.datasource.network.KtorClientFactory
import com.example.breezepoc.datasource.network.PeopleService
import com.example.breezepoc.datasource.network.PeopleServiceImpl

class NetworkModule {

    val peopleService: PeopleService by lazy {
        PeopleServiceImpl(
            httpClient = KtorClientFactory().build(),
            baseUrl = PeopleServiceImpl.BASE_URL
        )
    }
}