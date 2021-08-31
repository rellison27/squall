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

const val BEARER = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9hcGkuYnJlZXplY2htcy5jb21cL2FwaVwvdjJcL2F1dGhcL2xvZ2luIiwiaWF0IjoxNjMwNDA4NjcwLCJleHAiOjE2MzA0MTIyNzAsIm5iZiI6MTYzMDQwODY3MCwianRpIjoiQWE3M0RwUWNlZ2QzY2dhdyIsInN1YiI6Nzc1MTc2LCJwcnYiOiI0YWMwNWMwZjhhYzA4ZjM2NGNiNGQwM2ZiOGUxZjYzMWZlYzMyMmU4In0.EWUIWE8m70fUf4s6WFCAqwzkASU_Tl_DFbMJNH-ZBMw"
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
            val mapped = peopleNetworkMapper.mapToDomainList(people)
            println("KtorTest: ${mapped.getOrNull(0)?.id}")
            println("KtorTest: ${mapped.getOrNull(0)?.personDetails?.name?.first}")
            println("KtorTest: ${mapped.getOrNull(0)?.personDetails?.name?.last}")
            println("KtorTest: ${mapped.getOrNull(0)?.personDetails?.phone?.mobile}")
            println("KtorTest: ${mapped.getOrNull(0)?.personDetails?.email}")
            println("KtorTest: ${mapped.getOrNull(0)?.personDetails?.profilePicture}")

        }
        setContent{
            Navigation()
        }
    }
}
