package com.example.vottakvot.screen

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.example.vottakvot.ViewModel.InquirerViewModel
import com.example.vottakvot.ViewModel.SplashViewModel
import com.example.vottakvot.ViewModel.WelcomeViewModel
import com.example.vottakvot.navigation.Screen
import com.example.vottakvot.navigation.SetupNavGraph
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class,
    ExperimentalPagerApi::class
)
@Composable
fun MainScreen(
    context: Context,
    //splashViewModel: SplashViewModel,
    welcomeViewModel: WelcomeViewModel,
    inquirerViewModel: InquirerViewModel
) {
    val navHostController = rememberNavController()
    //val screen by splashViewModel.startDestination
    val screen = Screen.Splash.route
    val navController = rememberNavController()
    //val context =
    SetupNavGraph(
        context = context,
        navController = navController,
        startDestination = screen,
        welcomeViewModel = welcomeViewModel,
        inquirerViewModel = inquirerViewModel
    )

}