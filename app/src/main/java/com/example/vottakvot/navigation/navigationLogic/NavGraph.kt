package com.example.vottakvot.navigation.navigationLogic

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.navigation.screens.HomeScreen
import com.example.vottakvot.navigation.screens.SearchResultScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(
    context: Context,
    navController: NavHostController,
    isOnboardingPassed: Boolean,
    startDestination: String,
    trainListForYou: TrainListViewModel,
    trainListPopular: TrainListViewModel,
    trainListSearched: TrainListViewModel,
    splashScreenContent: @Composable () -> Unit,
    inquirerScreenContent: @Composable () -> Unit,
    welcomeScreenContent: @Composable () -> Unit,
    myTrainsContent: @Composable () -> Unit,
    favouriteContent: @Composable () -> Unit,
    profileContent: @Composable () -> Unit,
    //homeScreenOnboardingPassed: @Composable () -> Unit,
    //homeScreenWithoutOnbiarding: @Composable () -> Unit,
    //searchResultContent: @Composable () -> Unit,
    //searchResultForYouContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // сплеш экран
        composable(route = Screen.Splash.route) {
            LaunchedEffect(key1 = null){
                delay(2.seconds)
                navController.popBackStack()
                navController.navigate(Screen.Welcome.route)
            }
            splashScreenContent()
         }
        // приветственные экраны
        composable(route = Screen.Welcome.route) {
            welcomeScreenContent()
        }
        // экраны опросника
        composable(route = Screen.Inquirer.route) {
            inquirerScreenContent()
            // InquirerScreen(context = context , navController = navController, inquirerViewModel = inquirerViewModel)
        }
        // домашний экран после прохождения онбординга
        composable(route = Screen.Home.route) {
                HomeScreen(
                navController = navController,
                trainListForYou = trainListForYou,
                trainListPopular = trainListPopular,
                isOnboardingPassed = true
            )
        }
        /*
        // домашний экран после прохождения онбординга
        composable(route = Screen.HomeOnboardingPassed.route) {
            homeScreenOnboardingPassed()
        }
        // домашний экран бех онбординга
        composable(route = Screen.HomeWithoutOnboarding.route) {
            homeScreenWithoutOnbiarding()
        }
        */
        // страница с результатами поиска
        composable(route = Screen.SearchResult.route) {
            SearchResultScreen(
                navController = navController,
                title = stringResource(R.string.search_result),
                trainList = trainListSearched
            )
            //searchResultContent()
        }
        // страница "Больше тренировок для Вас"
        composable(route = Screen.SearchResultForYou.route) {
            SearchResultScreen(
                navController = navController,
                title = stringResource(R.string.workouts_for_you),
                trainList = trainListForYou
            )
            //searchResultForYouContent()
        }
        // страница "Больше тренировок для Вас"
        composable(route = Screen.SearchResultPopular.route) {
            SearchResultScreen(
                navController = navController,
                title = stringResource(R.string.popular),
                trainList = trainListPopular
            )
            //searchResultForYouContent()
        }

        composable(route = Screen.MyTrains.route) {
            myTrainsContent()
        }
        composable(route = Screen.Favourite.route) {
            favouriteContent()
        }
        composable(route = Screen.Profile.route) {
            profileContent()
        }
    }
}