package com.example.breezepoc.android.presentation.person_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import com.example.breezepoc.android.presentation.theme.AppTheme
import com.example.breezepoc.domain.model.Person.SinglePerson

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun PersonDetailScreen(
    person: SinglePerson?
) {
    AppTheme(displayProgressBar = false) {
        if (person == null) {
            Text("ERROR ${person}")
        } else {
            Column {
                Text("PersonDetailScreen: ${person.personDetails?.name?.first} ${person.personDetails?.name?.last}")
            }
        }
    }
}

//@Preview
//@Composable
//fun ComposableSceenDetail(){
//    PersonDetailScreen(personId = 0)
//}