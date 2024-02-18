package com.example.vottakvot.navigation.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vottakvot.ViewModel.InquirerViewModel
import com.example.vottakvot.ViewModel.SplashViewModel
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.ViewModel.WelcomeViewModel
import com.example.vottakvot.ViewModel.WorkoutViewModel
import com.example.vottakvot.database.WorkoutDataItem
import com.example.vottakvot.navigation.navigationLogic.BottomNavigationItem
import com.example.vottakvot.navigation.navigationLogic.Screen
import com.example.vottakvot.navigation.navigationLogic.SetupNavGraph
import com.google.accompanist.pager.ExperimentalPagerApi

//Вызов графа навигации по страницам
@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun MainScreen(
    context: Context,
    //generalViewModel: GeneralViewModel,
    splashViewModel: SplashViewModel,
    welcomeViewModel: WelcomeViewModel,
    inquirerViewModel: InquirerViewModel,
    workoutViewModel: WorkoutViewModel,
    trainListForYou: TrainListViewModel,
    trainListPopular: MutableLiveData<List<WorkoutDataItem>>,
    //trainListSearched: TrainListSearchedViewModel,
    isTrainListGets: MutableLiveData<Boolean>
) {
    // State of bottomBar, set state to false, if current page route is "car_details"
    val bottomBarState = rememberSaveable { (mutableStateOf(false)) }
    val navHostController = rememberNavController()
    // начальный экран
    val startDestination = splashViewModel.startDestination.value
        //Screen.Splash.route
    // пройден ли онбординг
    val isOnboardingPassed = splashViewModel.isOnBoardingCompleted.value
    val isWelcomePassed = splashViewModel.isWelcomeCompleted.value
        //true
    NavigationHomeScreen(
        navHostController = navHostController,
        screenContent = { ContentScreen(
            context = context,
            navHostController = navHostController,
            isOnboardingPassed = isOnboardingPassed,
            startDestination = startDestination,
            //generalViewModel = generalViewModel,
            splashViewModel = splashViewModel,
            welcomeViewModel = welcomeViewModel,
            inquirerViewModel = inquirerViewModel,
            workoutViewModel = workoutViewModel,
            trainListForYou = trainListForYou,
            trainListPopular = trainListPopular,
            //trainListSearched = trainListSearched,
            isTrainListGets = isTrainListGets
        ) }
    )
}

//  Функция для навигации
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationHomeScreen(
    navHostController: NavHostController,
    screenContent: @Composable (navHostController: NavHostController) -> Unit,
)
{
    //  нужно ли показывать нижнее меню
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    var showBottomBar by rememberSaveable { mutableStateOf(true) }
    showBottomBar = when (navBackStackEntry?.destination?.route) {
        Screen.Splash.route -> false // сплеш
        Screen.Welcome.route -> false // приветствие
        Screen.Loader.route -> false // подбор тренировок
        Screen.FindWorkouts.route -> false // подбор тренировок
        Screen.Inquirer.route -> false // опрос
        Screen.Workout.route -> false // тренировка
        Screen.Exercise.route -> false // упражнение
        Screen.Preparation.route -> false // подготовка к упражнению
        Screen.Doing.route -> false // выполнение упражнения
        Screen.Rest.route -> false // запуск между подходами
        else -> true // in all other cases show bottom bar
    }
    Scaffold(
        // показывать ли нижнюю навигацию на этой странице
        bottomBar = {
            if (showBottomBar)
                navBottomBar(
                    navHostController = navHostController,
                    navBackStackEntry = navBackStackEntry
                )
        }
    )
    {
        screenContent(navHostController)
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun navBottomBar(
    navHostController: NavHostController,
    navBackStackEntry: NavBackStackEntry?
)
{
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }


    val listItems = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.MyWorkouts,
        BottomNavigationItem.Favourite,
        BottomNavigationItem.Profile,
    )
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surface
        )
        {

            // название экрана, который сейчас отображается пользователю
            val currentRout = navBackStackEntry?.destination?.route
            listItems.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = currentRout == item.screen.route,
                    onClick = {
                        selectedItemIndex = index
                        navHostController.navigate(item.screen.route) {
                            // удалять из бэкстека всё до стартового экрана
                            popUpTo(Screen.Home.route) {
                                // сохраняет стейт экрана при удалении из бэкстека
                                saveState = true
                            }
                            // чтобы не перериосовывался повторно экран
                            // наверху бекстека будет лежать только одна копия экрана
                            launchSingleTop = true
                            // восстановить стейт при переходе на экран
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(item.icon, contentDescription = null)
                    },
                    label = {
                        Text(
                            text = stringResource(id = item.titleResId),
                            modifier = Modifier
                                .padding(0.dp)
                                .align(alignment = Alignment.CenterVertically)
                                .fillMaxHeight(0.2f),
                            fontSize = 10.sp,
                            maxLines = 1,
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.tertiary,
                        selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        selectedTextColor = MaterialTheme.colorScheme.onSurface, //colorScheme.onSurface,
                        unselectedIconColor = Color.White,//colorScheme.onSecondaryContainer, //colorScheme.onSurface,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurface//colorScheme.onSurface,

                    )
                )
            }
        }
    }

//  Содержимое страницы
@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun ContentScreen(
    context: Context,
    navHostController: NavHostController,
    isOnboardingPassed: Boolean,
    startDestination: String,
    //generalViewModel: GeneralViewModel,
    splashViewModel: SplashViewModel,
    welcomeViewModel: WelcomeViewModel,
    inquirerViewModel: InquirerViewModel,
    workoutViewModel: WorkoutViewModel,
    trainListForYou: TrainListViewModel,
    trainListPopular: MutableLiveData<List<WorkoutDataItem>>,
    isTrainListGets: MutableLiveData<Boolean>
) {
    //val navHostController = rememberNavController()
   // val screen = Screen.HomeOnboardingPassed.route
    //val screen =  Screen.Splash.route
    //val isOnboardingPassed: Boolean = true
    SetupNavGraph(
        navController = navHostController,
        startDestination = startDestination,
        splashScreenContent = { SplashScreen() },
        loaderScreenContent = { LoaderScreen(
            inquirerViewModel = inquirerViewModel,
            trainYoursList = trainListForYou,
            trainPopularList = trainListPopular,
            //trainListPopular,
            keyWord = "__"
        ) },
        welcomeScreenContent = {
            WelcomeScreen(
                navController = navHostController,
                welcomeViewModel = welcomeViewModel,
                inquirerViewModel = inquirerViewModel
            ) },
        inquirerScreenContent = { InquirerScreen(
            //context = context,
            navController = navHostController,
            inquirerViewModel = inquirerViewModel,
            trainListForYou = trainListForYou,
            isTrainListGets = isTrainListGets
        )
        },
        myTrainsContent = {
            MyTrainsScreen(
            navController = navHostController,
            trainList = trainListForYou
        )},
        editOneTrainsContent = {
            EditOneWorkoutScreen(
                navController = navHostController,
                trainList = trainListForYou
            )
            {
                trainListForYou.removeExercise(it.id)
            }
        },
        preparationContent = {
            PreparationScreen(
                navController = navHostController,
                trainList = trainListForYou,
                workoutItem = trainListForYou.findWorkoutById(trainListForYou.currentWorkoutId)
              )
        },
        doingContent = {
            DoingScreen(
                navController = navHostController,
                trainList = trainListForYou,
                workoutItem = trainListForYou.findWorkoutById(trainListForYou.currentWorkoutId)
            )
        },
        restContent = {
            RestScreen(
                navController = navHostController,
            trainList = trainListForYou,
            workoutItem = trainListForYou.findWorkoutById(trainListForYou.currentWorkoutId)
            )
        },
        favouriteContent = { FavouriteScreen(
            navController = navHostController,
            trainList = trainListForYou
        )},
        profileContent = { ProfileScreen()},
        splashViewModel = splashViewModel,
        inquirerViewModel = inquirerViewModel,
        trainListForYou = trainListForYou,
        trainListPopular = trainListPopular
    )
}


