package com.example.breezepoc.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.breezepoc.android.presentation.navigation.Navigation
import com.example.breezepoc.datasource.network.KtorClientFactory
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val BEARER = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9hcGkuYnJlZXplY2htcy5jb21cL2FwaVwvdjJcL2F1dGhcL2xvZ2luIiwiaWF0IjoxNjMwMDMwMTcwLCJleHAiOjE2MzAwMzM3NzAsIm5iZiI6MTYzMDAzMDE3MCwianRpIjoiRktMRUdSRFlMNkxTOUFSUyIsInN1YiI6Nzc1MTc2LCJwcnYiOiI0YWMwNWMwZjhhYzA4ZjM2NGNiNGQwM2ZiOGUxZjYzMWZlYzMyMmU4In0.ekzBAeq0MPI3BFM5mpFdwLHuTPsLA3jA-tn4CTVU51Y"
const val PEOPLE_URL = "https://api.breezechms.com/api/v2/people"

@ExperimentalStdlibApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // trying to get people
        val client = KtorClientFactory().build()
        CoroutineScope(Dispatchers.IO).launch {
            val peopleId = "/29468804"
            val person = client.get<String> {
                url("$PEOPLE_URL$peopleId")
                header("Authorization", BEARER,)
            }
            println("KtorTest: ${person}")
        }
        setContent{
            Navigation()
        }
    }
}
