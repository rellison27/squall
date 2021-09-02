package com.example.breezepoc.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.breezepoc.android.presentation.person_detail.PersonDetailScreen
import com.example.breezepoc.android.presentation.people_list.PeopleListScreen
import com.example.breezepoc.android.presentation.people_list.PeopleListViewModel
import com.example.breezepoc.android.presentation.person_detail.PersonDetailViewModel

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.PeopleList.route) {
        composable(route = Screen.PeopleList.route) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: PeopleListViewModel = viewModel("PeopleListViewModel", factory)
            PeopleListScreen(
                onSelectPerson = { personId ->
                    navController.navigate(Screen.personDetail.route + "/$personId")
                },
            )
        }
        composable(
            route = Screen.personDetail.route + "/{personId}",
            arguments = listOf(navArgument("personId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: PersonDetailViewModel = viewModel("PersonDetailViewModel", factory)
            PersonDetailScreen(person = viewModel.person.value)
        }
    }
}