package com.example.breezepoc.android.di

import com.example.breezepoc.datasource.network.KtorClientFactory
import com.example.breezepoc.datasource.network.PeopleService
import com.example.breezepoc.datasource.network.PeopleServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient{
        return KtorClientFactory().build()
    }

    @Singleton
    @Provides
    fun providePeopleService(
        httpClient: HttpClient,
    ): PeopleService {
        return PeopleServiceImpl(
            httpClient = httpClient,
            baseUrl = PeopleServiceImpl.BASE_URL
        )
    }
}