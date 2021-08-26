package com.example.breezepoc.android.presentation.person_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PersonDetailScreen(
    personId: Int?,
) {
    if (personId == null){
        Text("ERROR")
    }
    else{
        Text("PersonDetailScreen: $personId")
    }
}

@Preview
@Composable
fun ComposableSceenDetail(){
    PersonDetailScreen(personId = 0)
}