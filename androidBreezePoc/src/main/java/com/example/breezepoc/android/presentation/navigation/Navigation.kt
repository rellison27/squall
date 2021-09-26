package com.example.breezepoc.android.presentation.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.breezepoc.android.presentation.person_detail.PersonDetailScreen
import com.example.breezepoc.android.presentation.people_list.PeopleListScreen
import com.example.breezepoc.android.presentation.people_list.PeopleListViewModel
import com.example.breezepoc.android.presentation.person_detail.PersonDetailViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.animation.navigation

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun Navigation(){
BoxWithConstraints() {
    println("maxWidth: ${constraints.maxWidth}")
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = Screen.PeopleList.route) {
        composable(
            route = Screen.PeopleList.route,
            exitTransition = {_, _ ->
                slideOutHorizontally(
                    targetOffsetX = { -constraints.maxWidth },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                )
            },
            popEnterTransition = { _, _ ->
                slideInHorizontally(
                    initialOffsetX = { -constraints.maxWidth  },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                )
            },
        ) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: PeopleListViewModel = viewModel(key = "PeopleListViewModel", factory = factory)
            PeopleListScreen(
                state = viewModel.state.value,
                onSelectPerson = { personId ->
                    navController.navigate(Screen.personDetail.route + "/$personId")
                },
            )
        }
        composable(
            route = Screen.personDetail.route + "/{personId}",
            arguments = listOf(navArgument("personId")
            {
                type = NavType.IntType
            }),
            enterTransition = { _, _ ->
                slideInHorizontally(
                    initialOffsetX = { constraints.maxWidth },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                )
            },
            popExitTransition = { _, _ ->
                slideOutHorizontally(
                    targetOffsetX = { constraints.maxWidth },
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                )
            }
        ) { navBackStackEntry ->
            val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
            val viewModel: PersonDetailViewModel = viewModel(key = "PersonDetailViewModel", factory = factory)
            PersonDetailScreen(person = viewModel.person.value)
        }
    }
}

}