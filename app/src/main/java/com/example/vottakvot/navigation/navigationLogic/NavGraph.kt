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
import com.example.vottakvot.isOnboardingPassedApp
import com.example.vottakvot.navigation.screens.HomeScreen
import com.example.vottakvot.navigation.screens.LoaderScreen
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
    loaderScreenContent: @Composable () -> Unit,
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
                delay(5.seconds)
                navController.popBackStack()
                navController.navigate(Screen.Welcome.route)
            }
            splashScreenContent()
         }
        // сплеш экран
        composable(route = Screen.Loader.route) {
            LaunchedEffect(key1 = null){
                delay(10.seconds)
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            }
            loaderScreenContent()
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
                isOnboardingPassed = isOnboardingPassedApp
            )
        }
        // домашний экран без онбординга
        composable(route = Screen.HomeWithoutOnboarding.route) {
            HomeScreen(
                navController = navController,
                trainListForYou = trainListForYou,
                trainListPopular = trainListPopular,
                isOnboardingPassed = false
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
        // страница "Больше популярные"
        composable(route = Screen.SearchResultPopular.route) {
            SearchResultScreen(
                navController = navController,
                title = stringResource(R.string.popular),
                trainList = trainListPopular
            )
            //searchResultForYouContent()
        }

        //----------------------------------------- ТИПЫ ТРЕНИРОВКИ
        // страница "Уренняя зарядка"
        composable(route = Screen.SearchResultCharger.route) {
            SearchResultScreen(
                navController = navController,
                title = stringResource(R.string.charger),
                trainList = trainListForYou
            )
            //searchResultForYouContent()
        }
        composable(route = Screen.SearchResultHomeFitness.route) {
            SearchResultScreen(
                navController = navController,
                title = stringResource(R.string.home_fitness),
                trainList = trainListForYou
            )
            //searchResultForYouContent()
        }
        composable(route = Screen.SearchResultWorkFitness.route) {
            SearchResultScreen(
                navController = navController,
                title = stringResource(R.string.work_fitness),
                trainList = trainListForYou
            )
            //searchResultForYouContent()
        }
        composable(route = Screen.SearchResultBeforeBedtime.route) {
            SearchResultScreen(
                navController = navController,
                title = stringResource(R.string.before_bedtime),
                trainList = trainListForYou
            )
            //searchResultForYouContent()
        }
        //---------------------------------------------------------
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