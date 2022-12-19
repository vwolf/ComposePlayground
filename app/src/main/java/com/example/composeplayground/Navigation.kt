package com.example.composeplayground

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.composeplayground.AppScreens.FIRST_SCREEN
import com.example.composeplayground.AppScreens.MAIN_SCREEN
import com.example.composeplayground.AppScreens.SECOND_SCREEN

/**
 * Destinations used in [NavGraph]
 *
 */
object AppDestinations {
    const val MAIN_ROUTE = MAIN_SCREEN
    const val FIRSTSCREEN_ROUTE = FIRST_SCREEN
    const val SECONDSCREEN_ROUTE = SECOND_SCREEN
}

private object AppScreens {
    const val MAIN_SCREEN = "main"
    const val FIRST_SCREEN = "firstScreen"
    const val SECOND_SCREEN = "secondScreen"
}


class AppNavigationActions(private val navController: NavController) {

    fun navigateToMain() {
        navController.navigate(MAIN_SCREEN) {
            popUpTo(navController.graph.findStartDestination().id) {}
        }
    }

    fun navigateToFirstScreen() {
        navController.navigate(FIRST_SCREEN) {}
    }

    fun navigateToSecondScreen() {
        navController.navigate(SECOND_SCREEN)
    }
}