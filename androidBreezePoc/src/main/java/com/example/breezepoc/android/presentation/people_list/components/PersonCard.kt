package com.example.breezepoc.android.presentation.people_list.components

import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.breezepoc.android.R
import com.example.breezepoc.android.presentation.PERSON_IMAGE_LIST_HEIGHT
import com.example.breezepoc.android.presentation.PersonImage
import com.example.breezepoc.domain.model.Person.SinglePerson

@Composable
fun PersonCard(
    person: SinglePerson?,
    onClick: () -> Unit
) {
    val phone =
        if (person?.personDetails?.phone?.mobile?.number == null) "Phone" else person?.personDetails?.phone?.mobile?.number
    val email =
        if (person?.personDetails?.email?.address == null) "Email" else person?.personDetails?.email?.address
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
                start = 6.dp,
                end = 6.dp

            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            PersonImage(
                url = person?.personDetails?.profilePicture,
                contentDescription = person?.personDetails?.name?.first
            )
            Text(
                "${person?.personDetails?.name?.first} ${person?.personDetails?.name?.last}",
                style = MaterialTheme.typography.h5,
            )
            Column() {
                Text(
                    "$phone",
                    style = MaterialTheme.typography.overline
                )
                Text(
                    "$email",
                    style = MaterialTheme.typography.overline
                )
            }
            Column {
                Image(
                    modifier = Modifier
                        .size(30.dp),
                    contentDescription = "arrow",
                    painter = painterResource(R.drawable.ic_arrow_right)
                )
            }
        }
    }
}