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
            body = Auth("demo","demo","demo")
        }
        val mappedToken = tokenMapper.mapToDomainModel(response)
        println("Token $mappedToken")
        BEARER = "Bearer ${mappedToken.accessToken}"
        val people = httpClient.get<PeopleResponse> {
            url("$BASE_URL/$PEOPLE_PARAMS")
            header("Authorization", BEARER,)
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
         var BEARER = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9hcGkuYnJlZXplY2htcy5jb21cL2FwaVwvdjJcL2F1dGhcL2xvZ2luIiwiaWF0IjoxNjQ2MjMwMTI5LCJleHAiOjE2NDYyMzM3MjksIm5iZiI6MTY0NjIzMDEyOSwianRpIjoiTE9zUXB0NkhNREI2dFlobCIsInN1YiI6MzU1MzQ2LCJwcnYiOiI0YWMwNWMwZjhhYzA4ZjM2NGNiNGQwM2ZiOGUxZjYzMWZlYzMyMmU4In0.cbMYgdc9e5cvDTwjOtj5iW_ckDz2xnT8p1J0Y13_yjA"
        const val BASE_URL = "https://api.breezechms.com/api/v2/people"
        const val PEOPLE_PARAMS = "replacement?filter[or]=(filter[phone.mobile.number:exists]=true,filter[email.address:exists]=true,filter[birthdate:exists]=true,filter[family.role:exists]=true,filter[address.street:exists]=true)&filter[archived:exists]=false&sort=[last_name:asc,first_name:asc]"
    }

}