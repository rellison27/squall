package com.example.breezepoc.di

import com.example.breezepoc.datasource.cache.PeopleDatabase
import com.example.breezepoc.datasource.network.cache.DriverFactory
import com.example.breezepoc.datasource.network.cache.PeopleCache
import com.example.breezepoc.datasource.network.cache.PeopleCacheImpl
import com.example.breezepoc.datasource.network.cache.PeopleDatabaseFactory

class CacheModule {

    private val driverFactory: DriverFactory by lazy { DriverFactory() }
    val peopleDatabase: PeopleDatabase by lazy {
        PeopleDatabaseFactory(driverFactory = driverFactory).createDatabase()
    }
    val peopleCache: PeopleCache by lazy {
        PeopleCacheImpl(peopleDatabase = peopleDatabase)
    }
}