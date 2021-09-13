package com.example.breezepoc.datasource.network

import com.example.breezepoc.datasource.network.mappers.people.NameNetworkMapper
import com.example.breezepoc.datasource.network.mappers.people.PeopleNetworkMapper
import com.example.breezepoc.datasource.network.mappers.people.PersonDetailsNetworkMapper
import com.example.breezepoc.datasource.network.mappers.people.PhoneNetworkMapper
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
    // for People
    val nameNetworkMapper: NameNetworkMapper = NameNetworkMapper()
    val phoneNetworkMapper: PhoneNetworkMapper = PhoneNetworkMapper()
    val personDetailsNetworkMapper: PersonDetailsNetworkMapper = PersonDetailsNetworkMapper(
        nameNetworkMapper, phoneNetworkMapper
    )
    val peopleNetworkMapper: PeopleNetworkMapper = PeopleNetworkMapper(
        personDetailsNetworkMapper
    )

    // for Single Person call
    val singleNumberNetworkMapper: SingleNumberNetworkMapper = SingleNumberNetworkMapper()
    val emailNetworkMapper: SingleEmailNetworkMapper = SingleEmailNetworkMapper()
    val singlePhoneNetworkMapper: SinglePhoneNetworkMapper = SinglePhoneNetworkMapper(singleNumberNetworkMapper)
    val addressNetworkMapper: AddressNetworkMapper = AddressNetworkMapper()
    val singlePersonDetailsNetworkMapper: SinglePersonDetailsNetworkMapper = SinglePersonDetailsNetworkMapper(
        nameNetworkMapper,
        addressNetworkMapper,
singlePhoneNetworkMapper,
        emailNetworkMapper
        )
    val personNetworkMapper: PersonNetworkMapper = PersonNetworkMapper(singlePersonDetailsNetworkMapper)

    override suspend fun getPeople(): List<Person> {
        val people = httpClient.get<PeopleResponse> {
            url("$BASE_URL$PEOPLE_PARAMS")
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
        const val BEARER = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9hcGkuYnJlZXplY2htcy5jb21cL2FwaVwvdjJcL2F1dGhcL2xvZ2luIiwiaWF0IjoxNjMxNTM5Mjc3LCJleHAiOjE2MzE1NDI4NzcsIm5iZiI6MTYzMTUzOTI3NywianRpIjoiZG9SS2VLcGg0V0c4cENjTyIsInN1YiI6Nzc1MTc2LCJwcnYiOiI0YWMwNWMwZjhhYzA4ZjM2NGNiNGQwM2ZiOGUxZjYzMWZlYzMyMmU4In0.Z0MKTj_D5WinA_NQr_JweucNJWSpwHD6F_8IrFXyd0w"
        const val BASE_URL = "https://api.breezechms.com/api/v2/people"
        const val PEOPLE_PARAMS = "?sort=[formalName:asc]&filter[is_archived:eq:boolean]=false&filter[email:contains:text]=@,0,0&filter[phone:contains:text]=@:Mobile:false:false"
    }

}