package com.example.breezepoc.android.presentation.person_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.breezepoc.domain.model.Person.SinglePerson

@Composable
fun PersonDetailScreen(
    person: SinglePerson?
) {
    if (person == null) {
        Text("ERROR ${person}")
    } else {
        Column {
            Text("PersonDetailScreen: ${person.personDetails?.name?.first} ${person.personDetails?.name?.last}")
        }
    }
}

//@Preview
//@Composable
//fun ComposableSceenDetail(){
//    PersonDetailScreen(personId = 0)
//}