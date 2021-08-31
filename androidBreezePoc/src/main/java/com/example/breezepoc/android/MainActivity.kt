package com.example.breezepoc.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.breezepoc.android.presentation.navigation.Navigation
import com.example.breezepoc.datasource.network.KtorClientFactory
import com.example.breezepoc.datasource.network.PeopleServiceImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalStdlibApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // getting people
        CoroutineScope(Dispatchers.IO).launch {
            val peopleService = PeopleServiceImpl(
                httpClient = KtorClientFactory().build(),
                baseUrl = ""
            )
            val people = peopleService.getPeople()
            println("KtorTest: ${people.getOrNull(1)?.id}")
            println("KtorTest: ${people.getOrNull(1)?.personDetails?.name?.first}")
            println("KtorTest: ${people.getOrNull(1)?.personDetails?.name?.last}")
            println("KtorTest: ${people.getOrNull(1)?.personDetails?.phone?.mobile}")
            println("KtorTest: ${people.getOrNull(1)?.personDetails?.email}")
            println("KtorTest: ${people.getOrNull(1)?.personDetails?.profilePicture}")
        }
        setContent{
            Navigation()
        }
    }
}
