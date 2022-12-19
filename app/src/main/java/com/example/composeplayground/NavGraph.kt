package com.example.composeplayground

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph (
    applicationContext: Context,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppDestinations.MAIN_ROUTE,
    navAction: AppNavigationActions = remember(navController) {
        AppNavigationActions(navController)
    }
) {

    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(
            AppDestinations.MAIN_ROUTE
        ) {
            MainScreen(
                navAction = navAction,
                navController = navController)
        }
        composable(
            AppDestinations.FIRSTSCREEN_ROUTE
        ) {
            FirstScreen(navAction = navAction)
        }

        composable(
            AppDestinations.SECONDSCREEN_ROUTE
        ) {
            SecondScreen(navAction = navAction)
        }

    }

}