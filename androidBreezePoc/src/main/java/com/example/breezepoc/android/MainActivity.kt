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

        // getting person
//        CoroutineScope(Dispatchers.IO).launch {
//            val peopleService = PeopleServiceImpl(
//                httpClient = KtorClientFactory().build(),
//                baseUrl = ""
//            )
//            val person = peopleService.getPerson(26308818)
//            println("KtorTest: ${person?.id}")
//            println("KtorTest: ${person?.personDetails?.name?.first}")
//            println("KtorTest: ${person?.personDetails?.name?.last}")
//            println("KtorTest: ${person?.personDetails?.phone?.mobile?.number}")
//            println("KtorTest: ${person?.personDetails?.email?.address}")
//            println("KtorTest: ${person?.personDetails?.profilePicture}")
//        }
        setContent{
            Navigation()
        }
    }
}
