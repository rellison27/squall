package com.example.breezepoc.android.presentation.people_list.components

import android.widget.Space
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.breezepoc.android.R
import com.example.breezepoc.android.presentation.PERSON_IMAGE_LIST_HEIGHT
import com.example.breezepoc.android.presentation.PersonImage
import com.example.breezepoc.domain.model.PeopleList.Person
import com.example.breezepoc.domain.model.Person.SinglePerson

@Composable
fun PersonCard(
    person: Person?,
    onClick: () -> Unit
) {
    val phone =
        if (person?.phone?.mobile?.number == null) "Phone" else person.phone?.mobile?.number
    val email =
        if (person?.email?.address == null) "Email" else person.email?.address
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
//            .padding(
//                bottom = 4.dp,
//                top = 2.dp,
//                start = 2.dp,
//                end = 4.dp
//            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 6.dp, top = 6.dp, start = 6.dp, end = 6.dp)
                .fillMaxWidth()
        ) {
            PersonImage(
                url = person?.profilePicture,
                contentDescription = person?.name?.first
            )
            Spacer(modifier = Modifier.size(24.dp))
            Column(modifier = Modifier
                .weight(1f)) {
                Text(
                    "${person?.name?.first}",
                    style = MaterialTheme.typography.h5,
                )
                Text(
                    "${person?.name?.last}",
                    style = MaterialTheme.typography.h5,
                )
            }

            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    "$phone",
                    style = MaterialTheme.typography.overline
                )
                Text(
                    "$email",
                    style = MaterialTheme.typography.overline
                )
            }
            Row(horizontalArrangement = Arrangement.End) {
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