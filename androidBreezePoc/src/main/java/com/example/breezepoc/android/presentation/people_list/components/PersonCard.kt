package com.example.breezepoc.android.presentation.people_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.breezepoc.android.presentation.PersonImage
import com.example.breezepoc.domain.model.Person.SinglePerson

@Composable
fun PersonCard(
    person: SinglePerson?,
    onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
            PersonImage(url = person?.personDetails?.profilePicture, contentDescription = person?.personDetails?.name?.first)
            Column() {
                Text(
                    "${person?.personDetails?.name?.first} ${person?.personDetails?.name?.last}",
                    style = MaterialTheme.typography.h3
                )
                Text(
                    "${person?.personDetails?.phone?.mobile?.number}",
                    style = MaterialTheme.typography.h3
                )
                Text(
                    "${person?.personDetails?.email?.address}",
                    style = MaterialTheme.typography.h3
                )
            }
        }
    }
}