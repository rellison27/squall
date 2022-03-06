package com.example.breezepoc.android.presentation.person_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.breezepoc.android.presentation.PersonImage
import com.example.breezepoc.android.presentation.components.PersonDetailImage
import com.example.breezepoc.domain.model.Person.SinglePerson

@ExperimentalStdlibApi
@Composable
fun PersonView(
    person: SinglePerson,
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        item {
            BoxWithConstraints {
                Surface{
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        PersonDetailImage(
                            url = person.personDetails?.profilePicture,
                            contentDescription = person.personDetails?.name?.first
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 4.dp)
                            ){
                                Text(
                                    text = "${person.personDetails?.name?.first} ${person.personDetails?.name?.last}",
                                    modifier = Modifier
                                        .fillMaxWidth(0.85f)
                                        .wrapContentWidth(Alignment.Start),
                                    style = MaterialTheme.typography.h3
                                )
                            }

                        }
                        PersonDetailProperty(label = "Email", value = person.personDetails?.email?.address)
                        PersonDetailProperty(label = "Phone", value = person.personDetails?.phone?.mobile?.number)
                        PersonDetailProperty(label = "Street", value = person.personDetails?.address?.street)
                        PersonDetailProperty(label = "City", value = person.personDetails?.address?.city)
                        PersonDetailProperty(label = "State", value = person.personDetails?.address?.state)
                        PersonDetailProperty(label = "Zip", value = person.personDetails?.address?.zip)
                    }
                }
                
            }

        }
    }
}
