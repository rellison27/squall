package com.example.breezepoc.android.presentation.people_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.breezepoc.android.presentation.people_list.components.PeopleList
import com.example.breezepoc.android.presentation.theme.AppTheme

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun PeopleListScreen(
    state: PeopleListState,
    onSelectPerson: (Long?) -> Unit,
) {

    AppTheme(displayProgressBar = false) {
        PeopleList(
            loading = state.isLoading,
            people = state.people,
            onClickPersonListItem = onSelectPerson
        )
    }

}

//@Preview
//@Composable
//fun ComposablePreview(){
//    PeopleListScreen(onSelectPerson = )
//}