package com.example.vottakvot.navigation.navigationLogic

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.InquirerViewModel
import com.example.vottakvot.ViewModel.SplashViewModel
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.database.WorkoutDataItem
import com.example.vottakvot.navigation.screens.ExerciseScreen
import com.example.vottakvot.navigation.screens.FilterScreen
import com.example.vottakvot.navigation.screens.HomeScreen
import com.example.vottakvot.navigation.screens.SearchResultScreen
import com.example.vottakvot.navigation.screens.WorkoutScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String,
    splashViewModel: SplashViewModel,
    inquirerViewModel: InquirerViewModel,
    trainListForYou: TrainListViewModel,
    trainListPopular: MutableLiveData<List<WorkoutDataItem>>,
    splashScreenContent: @Composable () -> Unit,
    loaderScreenContent: @Composable () -> Unit,
    inquirerScreenContent: @Composable () -> Unit,
    welcomeScreenContent: @Composable () -> Unit,
    myTrainsContent: @Composable () -> Unit,
    favouriteContent: @Composable () -> Unit,
    profileContent: @Composable () -> Unit,
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
                navController.navigate(startDestination)
                    //Screen.Welcome.route)
            }
            splashScreenContent()
         }
        // сплеш экран
        composable(route = Screen.Loader.route) {
            LaunchedEffect(key1 = null) {
                delay(5.seconds)
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            }
            loaderScreenContent()
        }
        composable(route = Screen.FindWorkouts.route) {
            LaunchedEffect(key1 = null){
                delay(5.seconds)
                navController.popBackStack()
                navController.navigate(Screen.SearchResult.route)
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
        val isOnboarding = splashViewModel.isOnBoardingCompleted.value
        // домашний экран после прохождения онбординга
        composable(route = Screen.Home.route) {
                HomeScreen(
                navController = navController,
                trainListForYou = trainListForYou,
                trainListPopular = trainListPopular,
                isOnboardingPassed = isOnboarding,
            )
        }
        composable(route = Screen.Exercise.route) {
            ExerciseScreen(
                navController = navController,
                trainList = trainListForYou
            )
        }
        composable(route = Screen.Workout.route) {
            WorkoutScreen(
                navController = navController,
                trainList = trainListForYou
            )
        }
        // домашний экран без онбординга
        composable(route = Screen.HomeWithoutOnboarding.route) {
            HomeScreen(
                navController = navController,
                trainListForYou = trainListForYou,
                trainListPopular = trainListPopular,
                isOnboardingPassed = false,
                //isTrainListGets = isTrainListGets
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

        composable(route = Screen.Filter.route) {
            FilterScreen(
                navController = navController,
                title = stringResource(R.string.find_workouts),
                trainList = trainListForYou,
                //trainListSearched,
                inquirerViewModel = inquirerViewModel //InquirerViewModel(DataStoreRepository(context))
            )
            //searchResultContent()
        }
        // страница с результатами поиска
        composable(route = Screen.SearchResult.route) {
            SearchResultScreen(
                navController = navController,
                title = stringResource(R.string.search_result),
                trainList = trainListForYou
                //trainListSearched
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
                trainList = trainListForYou
                //trainListPopular
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