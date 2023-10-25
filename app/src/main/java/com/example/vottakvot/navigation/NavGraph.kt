package com.example.vottakvot.navigation

import android.app.appsearch.SearchResult
import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.GeneralViewModel
import com.example.vottakvot.ViewModel.InquirerViewModel
import com.example.vottakvot.ViewModel.WelcomeViewModel
import com.example.vottakvot.ViewModel.WorkoutViewModel
import com.example.vottakvot.screen.HomeScreenWithOnboarding
import com.example.vottakvot.screen.HomeScreenWithoutOnboarding
import com.example.vottakvot.screen.InquirerScreen
import com.example.vottakvot.screen.NavigationSearchResult
import com.example.vottakvot.screen.SplashScreen
import com.example.vottakvot.screen.WelcomeScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(
    context: Context,
    navController: NavHostController,
    startDestination: String,
    generalViewModel: GeneralViewModel,
    //splashViewModel: SplashViewModel,
    welcomeViewModel: WelcomeViewModel,
    inquirerViewModel: InquirerViewModel,
    workoutViewModel: WorkoutViewModel
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Splash.route) {
            LaunchedEffect(key1 = null){
                delay(2.seconds)
                navController.popBackStack()
                navController.navigate(Screen.Welcome.route)
            }
            SplashScreen()
         }
        // add destination
        composable(route = Screen.Inquirer.route) {
            InquirerScreen(context = context , navController = navController, inquirerViewModel = inquirerViewModel)
        }
        // add destination
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(
                //context = context ,
                navController = navController, welcomeViewModel = welcomeViewModel)
        }
        // онбординг пройден
        composable(route = Screen.HomeOnboardingPassed.route) {
            HomeScreenWithOnboarding(workoutViewModel = workoutViewModel)
        }
        // онбординг не пройден
        composable(route = Screen.HomeWithoutOnboarding.route) {
            HomeScreenWithoutOnboarding()
        }
        // страница поиска
        composable(route = Screen.SearchResult.route) {
            NavigationSearchResult( generalViewModel = generalViewModel,
                workoutViewModel = workoutViewModel,
                stringResource(R.string.search_result))

        }
    }
}