package com.example.breezepoc.android.presentation.people_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PeopleListScreen(
    onSelectPerson: (Int?) -> Unit,
) {
    val peopleIds: List<Int> = listOf(
        29468804,
        26308818,
        26308820,
        26308832,
        26308858,
        26308854,
        26308856,
        33456976,
        33456974,
        26308848,
        26308852,
        26308850,
        26308826,
        26308822,
        26308824,
        26308828,
        26308846,
        26308840,
        26308842,
        26308844,
        26308866,
        26308830,
        26308868,
        26308860,
        31708408,
        26308864,
        26308862,
        26308838,
        26308836,
        26308834
    )
//    Text(text = "KtorTest: ${people.value?.getOrNull(1)?.id}")
    LazyColumn (Modifier.fillMaxWidth()) {
        items(peopleIds){personId ->
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSelectPerson(personId)
                    }
                    ){
                Text(
                    modifier = Modifier
                        .padding(16.dp),
                    text = "PersonId = ${personId}"
                )
            }
        }
    }
}

//@Preview
//@Composable
//fun ComposablePreview(){
//    PeopleListScreen(onSelectPerson = )
//}