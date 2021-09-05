package com.example.breezepoc.datasource.network.cache

import android.content.Context
import com.example.breezepoc.datasource.cache.PeopleDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(PeopleDatabase.Schema, context, "people.db")
    }
}