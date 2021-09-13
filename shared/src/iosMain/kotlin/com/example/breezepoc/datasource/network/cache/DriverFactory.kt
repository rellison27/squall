package com.example.breezepoc.datasource.network.cache

import com.example.breezepoc.datasource.cache.PeopleDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(PeopleDatabase.Schema, "people.db")
    }
}