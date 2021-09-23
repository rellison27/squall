package com.example.breezepoc.android.presentation.people_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.breezepoc.android.presentation.PERSON_IMAGE_LIST_HEIGHT
import com.example.breezepoc.domain.model.PeopleList.Person

@Composable
fun PeopleList(
    loading: Boolean,
    people: List<Person>,
    onClickPersonListItem: (Long) -> Unit
){
    Box(modifier = Modifier
        .background(color = MaterialTheme.colors.surface)
    ) {
        if (loading && people.isEmpty()) {
            LoadingPeopleListShimmer(imageHeight = PERSON_IMAGE_LIST_HEIGHT.dp)
        } else if (people.isEmpty()) {
            // nothing is here
        } else {
            LazyColumn{
                itemsIndexed(
                    items = people
                ) {index, person ->
                    PersonCard(
                        person = person,
                        onClick = {
                            onClickPersonListItem(person.id)
                        }
                    )
                }
            }
        }
    }
}