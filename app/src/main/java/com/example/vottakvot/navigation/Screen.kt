package com.example.vottakvot.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen(route = "welcome_screen")
    object Inquirer : Screen(route = "inquirer_screen")
    object Home : Screen(route = "home_screen")
}