package com.example.breezepoc.android.presentation.people_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp

@Composable
fun PeopleListScreen(
    onSelectPerson: (Int) -> Unit,
) {
    LazyColumn {
        items(100) { personId ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSelectPerson(personId)
                    }
            ) {
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