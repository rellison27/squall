package com.example.breezepoc.datasource.network

import com.example.breezepoc.datasource.network.mappers.people.*
import com.example.breezepoc.datasource.network.mappers.person.*
import com.example.breezepoc.datasource.network.model.PeopleResponse
import com.example.breezepoc.datasource.network.model.PersonResponse
import com.example.breezepoc.domain.model.PeopleList.Person
import com.example.breezepoc.domain.model.Person.SinglePerson
import io.ktor.client.*
import io.ktor.client.request.*

class PeopleServiceImpl(
    private val httpClient: HttpClient,
    // great  to have for testing even though I'm hardcoding the bseurl below
    private val baseUrl: String,
): PeopleService {
    val numberNetworkMapper: NumberNetworkMapper = NumberNetworkMapper()
    val phoneNetworkMapper: PhoneNetworkMapper = PhoneNetworkMapper(numberNetworkMapper)
    val addressNetworkMapper: AddressNetworkMapper = AddressNetworkMapper()
    // for People
    val nameNetworkMapper: NameNetworkMapper = NameNetworkMapper()
    val emailNetworkMapper: EmailNetworkMapper = EmailNetworkMapper()
    val peopleNetworkMapper: PeopleNetworkMapper = PeopleNetworkMapper(
        nameNetworkMapper,
        phoneNetworkMapper,
        addressNetworkMapper,
        emailNetworkMapper
    )

    // for Single Person call

    val singleEmailNetworkMapper: SingleEmailNetworkMapper = SingleEmailNetworkMapper()

    val singlePersonDetailsNetworkMapper: SinglePersonDetailsNetworkMapper = SinglePersonDetailsNetworkMapper(
        nameNetworkMapper,
        addressNetworkMapper,
        phoneNetworkMapper,
        singleEmailNetworkMapper
        )
    val personNetworkMapper: PersonNetworkMapper = PersonNetworkMapper(singlePersonDetailsNetworkMapper)

    override suspend fun getPeople(): List<Person> {
        val people = httpClient.get<PeopleResponse> {
            url("$BASE_URL/$PEOPLE_PARAMS")
            header("Authorization", BEARER,)
        }.data
        val mapped = peopleNetworkMapper.mapToDomainList(people)
        return mapped
    }

    override suspend fun getPerson(id: Int): SinglePerson {
        val person =  httpClient.get<PersonResponse>{
            // might want to hard code base url for this
            url("$BASE_URL/$id")
            header("Authorization", BEARER)
        }.data

        val mapped = personNetworkMapper.mapToDomainModel(person)
        return mapped
    }

    companion object {
        const val BEARER = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9hcGkuYnJlZXplY2htcy5jb21cL2FwaVwvdjJcL2F1dGhcL2xvZ2luIiwiaWF0IjoxNjMyNDI0MzcxLCJleHAiOjE2MzI0Mjc5NzEsIm5iZiI6MTYzMjQyNDM3MSwianRpIjoiRm5ScGZ5aFZSclJpQlBkVCIsInN1YiI6Nzc1MTc2LCJwcnYiOiI0YWMwNWMwZjhhYzA4ZjM2NGNiNGQwM2ZiOGUxZjYzMWZlYzMyMmU4In0.7g4-slgXe3vk956zG2CWShTHwq2arGxsFsJr1RqZcps"
        const val BASE_URL = "https://api.breezechms.com/api/v2/people"
        const val PEOPLE_PARAMS = "replacement?sort=[last_name:asc,first_name:asc]&filter[archived:exists]=false"
    }

}