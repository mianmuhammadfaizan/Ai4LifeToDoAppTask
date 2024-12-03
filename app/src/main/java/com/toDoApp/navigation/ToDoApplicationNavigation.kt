package com.toDoApp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ToDoApplicationNavigation(navController: NavHostController) {

    val startDestination = rememberSaveable {
        mutableStateOf(
            ToDoAppScreens.DashboardScannerScreen.name
        )
    }


    NavHost(
        navController = navController,
        startDestination = startDestination.value,

        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },

        ) {

        dashboardScreen(navController = navController)

    }

}