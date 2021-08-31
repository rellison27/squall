package com.example.breezepoc.datasource.network

import io.ktor.client.*

expect class KtorClientFactory() {
    fun build(): HttpClient
}