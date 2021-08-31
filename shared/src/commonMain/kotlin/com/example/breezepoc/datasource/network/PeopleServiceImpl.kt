package com.example.breezepoc.datasource.network

import com.example.breezepoc.datasource.network.mappers.people.NameNetworkMapper
import com.example.breezepoc.datasource.network.mappers.people.PeopleNetworkMapper
import com.example.breezepoc.datasource.network.mappers.people.PersonDetailsNetworkMapper
import com.example.breezepoc.datasource.network.mappers.people.PhoneNetworkMapper
import com.example.breezepoc.datasource.network.model.PeopleResponse
import com.example.breezepoc.domain.model.Person
import io.ktor.client.*
import io.ktor.client.request.*

class PeopleServiceImpl(
    private val httpClient: HttpClient,
    private val baseUrl: String,
): PeopleService {
    val nameNetworkMapper: NameNetworkMapper = NameNetworkMapper()
    val phoneNetworkMapper: PhoneNetworkMapper = PhoneNetworkMapper()
    val personDetailsNetworkMapper: PersonDetailsNetworkMapper = PersonDetailsNetworkMapper(
        nameNetworkMapper, phoneNetworkMapper
    )
    val peopleNetworkMapper: PeopleNetworkMapper = PeopleNetworkMapper(
        personDetailsNetworkMapper
    )

    override suspend fun getPeople(): List<Person> {
        val people = httpClient.get<PeopleResponse> {
            url("$BASE_URL")
            header("Authorization", BEARER,)
        }.data
        val mapped = peopleNetworkMapper.mapToDomainList(people)
        return mapped
    }

    override suspend fun getPerson(id: Long): Person {
        TODO("Not yet implemented")
        // will use baseUrl here
    }

    companion object {
        const val BEARER = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9hcGkuYnJlZXplY2htcy5jb21cL2FwaVwvdjJcL2F1dGhcL2xvZ2luIiwiaWF0IjoxNjMwNDEzMDI2LCJleHAiOjE2MzA0MTY2MjYsIm5iZiI6MTYzMDQxMzAyNiwianRpIjoiNEpFTmNXd3NSWHR4QlFpbCIsInN1YiI6Nzc1MTc2LCJwcnYiOiI0YWMwNWMwZjhhYzA4ZjM2NGNiNGQwM2ZiOGUxZjYzMWZlYzMyMmU4In0._oEBaPe7RQNyiTIjXZGdzz97a0BfScF2hqyhY_UAWRQ"
        const val BASE_URL = "https://api.breezechms.com/api/v2/people?sort=[formalName:asc]&filter[is_archived:eq:boolean]=false&filter[email:contains:text]=@,0,0&filter[phone:contains:text]=@:Mobile:false:false"
    }

}