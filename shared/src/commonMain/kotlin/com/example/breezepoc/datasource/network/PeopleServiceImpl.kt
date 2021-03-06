package com.example.breezepoc.datasource.network

import com.example.breezepoc.datasource.network.mappers.login.TokenMapper
import com.example.breezepoc.datasource.network.mappers.people.*
import com.example.breezepoc.datasource.network.mappers.person.*
import com.example.breezepoc.datasource.network.model.AuthResponse
import com.example.breezepoc.datasource.network.model.PeopleResponse
import com.example.breezepoc.datasource.network.model.PersonResponse
import com.example.breezepoc.domain.model.Login.Auth
import com.example.breezepoc.domain.model.PeopleList.Person
import com.example.breezepoc.domain.model.Person.SinglePerson
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

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

    // for Auth
    val tokenMapper: TokenMapper = TokenMapper()

    override suspend fun getPeople(): List<Person> {
        val response: AuthResponse = httpClient.post<AuthResponse> {
            url("https://api.breezechms.com/api/v2/auth/login")
            contentType(ContentType.Application.Json)
            body = Auth("demo","demo","rashaune")
        }
        val mappedToken = tokenMapper.mapToDomainModel(response)
        println("Token $mappedToken")
        val bearerToken = "Bearer ${mappedToken.accessToken}"
        val people = httpClient.get<PeopleResponse> {
            url("$BASE_URL/$PEOPLE_PARAMS")
            header("Authorization", bearerToken,)
        }.data
        val mapped = peopleNetworkMapper.mapToDomainList(people)
        println("Testing123 $mapped")
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
        // old manual way of logging in
         var BEARER = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9hcGkuYnJlZXplY2htcy5jb21cL2FwaVwvdjJcL2F1dGhcL2xvZ2luIiwiaWF0IjoxNjQ3MDI2MDIyLCJleHAiOjE2NDcwMjk2MjIsIm5iZiI6MTY0NzAyNjAyMiwianRpIjoialV2S3pRR2xPamtCMnlDbSIsInN1YiI6Nzc1MTc2LCJwcnYiOiI0YWMwNWMwZjhhYzA4ZjM2NGNiNGQwM2ZiOGUxZjYzMWZlYzMyMmU4In0.156kx3R1t6k6Vn0gAO3ZJvN8p3V1sLhjns8_cRSwCRU"
        const val BASE_URL = "https://api.breezechms.com/api/v2/people"
        const val PEOPLE_PARAMS = "replacement?filter[or]=(filter[phone.mobile.number:exists]=true,filter[email.address:exists]=true,filter[birthdate:exists]=true,filter[family.role:exists]=true,filter[address.street:exists]=true)&filter[archived:exists]=false&sort=[last_name:asc,first_name:asc]"
    }

}