package com.example.vottakvot.navigation

import androidx.compose.runtime.Composable

sealed class  Screen(
    val route: String
) {
    object Splash : Screen(route = ROUTE_SPLASH_SCREEN)
    object Welcome : Screen(route = ROUTE_WELCOME_SCREEN)
    object Inquirer : Screen(route = ROUTE_INQUIRER_SCREEN)
    object Home : Screen(route = ROUTE_HOME_SCREEN)

    private companion object {
        const val ROUTE_SPLASH_SCREEN = "splash_screen"
        const val ROUTE_WELCOME_SCREEN = "welcome_screen"
        const val ROUTE_INQUIRER_SCREEN = "inquirer_screen"
        const val ROUTE_HOME_SCREEN = "home_screen"
    }

}