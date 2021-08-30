package com.example.breezepoc.datasource.network

import com.example.breezepoc.datasource.network.mappers.people.NameNetworkMapper
import com.example.breezepoc.datasource.network.mappers.people.PeopleListNetworkMapper
import com.example.breezepoc.datasource.network.mappers.people.PersonDetailsNetworkMapper
import com.example.breezepoc.datasource.network.mappers.people.PhoneNetworkMapper
import com.example.breezepoc.datasource.network.model.PeopleResponse
import com.example.breezepoc.domain.model.*
import com.example.breezepoc.domain.model.util.DtoMapper
import io.ktor.client.*

expect class KtorClientFactory() {
    fun build(): HttpClient
}