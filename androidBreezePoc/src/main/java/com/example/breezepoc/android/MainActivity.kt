package com.example.breezepoc.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.breezepoc.android.presentation.navigation.Navigation
import com.example.breezepoc.datasource.network.KtorClientFactory
import com.example.breezepoc.datasource.network.mappers.people.NameNetworkMapper
import com.example.breezepoc.datasource.network.mappers.people.PeopleNetworkMapper
import com.example.breezepoc.datasource.network.mappers.people.PersonDetailsNetworkMapper
import com.example.breezepoc.datasource.network.mappers.people.PhoneNetworkMapper
import com.example.breezepoc.datasource.network.model.PeopleDto
import com.example.breezepoc.datasource.network.model.PeopleResponse
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val BEARER = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9hcGkuYnJlZXplY2htcy5jb21cL2FwaVwvdjJcL2F1dGhcL2xvZ2luIiwiaWF0IjoxNjMwMzcxOTQ0LCJleHAiOjE2MzAzNzU1NDQsIm5iZiI6MTYzMDM3MTk0NCwianRpIjoiYzVPZDNjZmR1VUdNWU9yaiIsInN1YiI6Nzc1MTc2LCJwcnYiOiI0YWMwNWMwZjhhYzA4ZjM2NGNiNGQwM2ZiOGUxZjYzMWZlYzMyMmU4In0.MAMQc4b4aFG0bOUpGPmwC8YNGK1prJtV1x-ikcEpxRc"
const val PEOPLE_URL = "https://api.breezechms.com/api/v2/people?sort=[formalName:asc]&filter[is_archived:eq:boolean]=false&filter[email:contains:text]=@,0,0&filter[phone:contains:text]=@:Mobile:false:false"

@ExperimentalStdlibApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nameNetworkMapper: NameNetworkMapper = NameNetworkMapper()
        val phoneNetworkMapper: PhoneNetworkMapper = PhoneNetworkMapper()
        val personDetailsNetworkMapper: PersonDetailsNetworkMapper = PersonDetailsNetworkMapper(
            nameNetworkMapper, phoneNetworkMapper
        )
        val peopleNetworkMapper: PeopleNetworkMapper = PeopleNetworkMapper(
            personDetailsNetworkMapper
        )






        // trying to get people
        val client = KtorClientFactory().build()
        CoroutineScope(Dispatchers.IO).launch {
            val people = client.get<PeopleResponse> {
                url("$PEOPLE_URL")
                header("Authorization", BEARER,)
            }.data
            val mapped = peopleNetworkMapper.mapFromDomainList(people)
            println("KtorTest: ${mapped}")
        }
        setContent{
            Navigation()
        }
    }
}
