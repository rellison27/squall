package com.example.breezepoc.android.di

import com.example.breezepoc.datasource.network.PeopleService
import com.example.breezepoc.datasource.network.cache.PeopleCache
import com.example.breezepoc.interactors.people_list.PopulatePeopleList
import com.example.breezepoc.interactors.person_detail.PopulatePersonDetail
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun providePopulatePeopleList(
        peopleService: PeopleService,
        peopleCache: PeopleCache
    ): PopulatePeopleList {
        return PopulatePeopleList(peopleService = peopleService, peopleCache = peopleCache)
    }

    @Singleton
    @Provides
    fun providePopulatePersonDetail(
        peopleCache: PeopleCache
    ): PopulatePersonDetail {
        return PopulatePersonDetail(peopleCache = peopleCache)
    }
}