package com.example.breezepoc.android.di

import android.content.Context
import com.example.breezepoc.android.BaseApplication
import com.example.breezepoc.datasource.cache.PeopleDatabase
import com.example.breezepoc.datasource.network.cache.DriverFactory
import com.example.breezepoc.datasource.network.cache.PeopleCache
import com.example.breezepoc.datasource.network.cache.PeopleCacheImpl
import com.example.breezepoc.datasource.network.cache.PeopleDatabaseFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun providePeoplDataBase(context: BaseApplication): PeopleDatabase {
        return PeopleDatabaseFactory(driverFactory = DriverFactory(context)).createDatabase()
    }

    @Singleton
    @Provides
    fun providePeopleCache(
        peopleDatabase: PeopleDatabase
    ): PeopleCache {
        return PeopleCacheImpl(peopleDatabase = peopleDatabase)
    }
}