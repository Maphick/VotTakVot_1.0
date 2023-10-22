package com.example.vottakvot.navigation

import androidx.compose.runtime.Composable

sealed class  Screen(
    val route: String
) {
    object Splash : Screen(route = ROUTE_SPLASH_SCREEN)
    object Welcome : Screen(route = ROUTE_WELCOME_SCREEN)
    object Inquirer : Screen(route = ROUTE_INQUIRER_SCREEN)
    object Home : Screen(route = ROUTE_HOME_SCREEN)
    object HomeOnboardingPassed : Screen(route = ROUTE_HOME_WITH_ONBOARDING)
    object HomeWithoutOnboarding : Screen(route = ROUTE_HOME_WITHOUT_ONBOARDING)

    private companion object {
        const val ROUTE_SPLASH_SCREEN = "splash_screen"
        const val ROUTE_WELCOME_SCREEN = "welcome_screen"
        const val ROUTE_INQUIRER_SCREEN = "inquirer_screen"
        const val ROUTE_HOME_SCREEN = "home_screen"
        const val ROUTE_HOME_WITH_ONBOARDING = "home_with_onboarding"
        const val ROUTE_HOME_WITHOUT_ONBOARDING = "home_without_onboarding"
    }

}