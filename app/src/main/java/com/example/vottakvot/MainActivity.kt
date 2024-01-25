package com.example.vottakvot

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.vottakvot.ViewModel.InquirerViewModel
import com.example.vottakvot.ViewModel.SplashViewModel
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.ViewModel.WelcomeViewModel
import com.example.vottakvot.ViewModel.WorkoutViewModel
import com.example.vottakvot.data.DataStoreRepository
import com.example.vottakvot.database.WorkoutDataItem
import com.example.vottakvot.navigation.screens.MainScreen
import com.example.vottakvot.ui.theme.VotTakVotTheme
import com.google.accompanist.pager.ExperimentalPagerApi

// пройден ли онбординг
var isOnboardingPassedApp = false
// получен ли список тренировок
var getYourTrainsSuccessed = false


@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)



    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // заставочка

        //val generalViewModel:GeneralViewModel = GeneralViewModel()
        var splashViewModel: SplashViewModel = SplashViewModel(DataStoreRepository(context = this) )
        //installSplashScreen().setKeepOnScreenCondition {
        //   !splashViewModel.isLoading.value
        //}

        // для экранов приветствия
        var welcomeViewModel = WelcomeViewModel(DataStoreRepository(this))
        // var welcomeViewModel = WelcomeViewModel(DataStoreRepository(this))
        // для экранов загрузки
        var inquirerViewModel = InquirerViewModel(DataStoreRepository(this))
        // вью модель для карточки тренировки
        var workoutViewModel = WorkoutViewModel()
        // список тренировок для Вас
        //var trainListForYou: TrainListViewModel = TrainListViewModel()
        // список популярных тренировок
        //val trainListPopular: TrainListViewModel = TrainListViewModel()//source = sourceListPopularExample)
        //  список тренировок для результатов поиска
        //val trainListSearched: TrainListViewModel = TrainListViewModel(source = sourceListSearchResultExample)

        val isTrainListGets: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

        // список тренировок для Вас
        var trainListForYou = ViewModelProvider(this).get(TrainListViewModel::class.java)

        // список популярных тренировок
        //val trainListPopular = ViewModelProvider(this).get(TrainListViewModel::class.java)

        //  список моих тренировок
        //val trainListMy = ViewModelProvider(this).get(TrainListViewModel::class.java)

        //  список избранных тренировок
        //val trainListFavourite = ViewModelProvider(this).get(TrainListViewModel::class.java)

        //  список тренировок для результатов поиска
        val trainListSearched = ViewModelProvider(this).get(TrainListViewModel::class.java)


        val workoutListPopular =  MutableLiveData<List<WorkoutDataItem>>()


        setContent {
            VotTakVotTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorScheme.background)
                        .padding(0.dp),
                ) {
                    val context = applicationContext
                    //val trainList : TrainListViewModel by ViewModels()
                    //val trainsForYou by viewModels<TrainListViewModel>(

                   // )

                    MainScreen(
                        context = context,
                        //generalViewModel = generalViewModel,
                        splashViewModel = splashViewModel,
                        welcomeViewModel = welcomeViewModel,
                        inquirerViewModel = inquirerViewModel,
                        workoutViewModel = workoutViewModel,
                        trainListForYou = trainListForYou,
                        trainListPopular = workoutListPopular,
                        //trainListSearched = trainListSearched,
                        isTrainListGets = isTrainListGets
                    )
                }
            }
        }
    }
}
