package com.example.breezepoc.datasource.network.cache
import com.example.breezepoc.datasource.cache.PeopleDatabase
import com.squareup.sqldelight.db.SqlDriver

class PeopleDatabaseFactory(
    private val driverFactory: DriverFactory
) {
    fun createDatabase():PeopleDatabase {
        return PeopleDatabase(driverFactory.createDriver())
    }
}

expect class DriverFactory {
    fun createDriver(): SqlDriver
}