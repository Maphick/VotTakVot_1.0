package com.example.vottakvot.navigation

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vottakvot.ViewModel.InquirerViewModel
import com.example.vottakvot.ViewModel.SplashViewModel
import com.example.vottakvot.ViewModel.WelcomeViewModel
import com.example.vottakvot.screen.HomeScreen
import com.example.vottakvot.screen.InquirerScreen
import com.example.vottakvot.screen.WelcomeScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(
    context: Context,
    navController: NavHostController,
    startDestination: String,
    //splashViewModel: SplashViewModel,
    welcomeViewModel: WelcomeViewModel,
    inquirerViewModel: InquirerViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route
    ) {
        // add destination
        composable(route = Screen.Inquirer.route) {
            //InquirerScreen(navController = navController)
            InquirerScreen(context = context , navController = navController, inquirerViewModel = inquirerViewModel)
        }
        // add destination
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(context = context, navController = navController, welcomeViewModel = welcomeViewModel)
        }

        // add destination
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
    }
}