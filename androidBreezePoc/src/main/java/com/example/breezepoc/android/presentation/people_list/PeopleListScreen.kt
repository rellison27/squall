package com.example.breezepoc.android.presentation.people_list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.breezepoc.android.presentation.people_list.components.PeopleList
import com.example.breezepoc.android.presentation.theme.AppTheme
import com.example.breezepoc.presentation.people_list.PeopleListState

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun PeopleListScreen(
    state: PeopleListState,
    onSelectPerson: (Long?) -> Unit,
) {

    AppTheme(displayProgressBar = state.isLoading) {
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