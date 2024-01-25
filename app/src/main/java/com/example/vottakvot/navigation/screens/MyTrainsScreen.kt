package com.example.vottakvot.navigation.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.PlaylistAdd
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.navigation.navigationLogic.Screen
import com.example.vottakvot.ui.theme.WorkoutCard
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun MyTrainsScreen(
    navController: NavHostController,
    trainList: TrainListViewModel
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.87f)
            .background(MaterialTheme.colorScheme.background),
    ) {
        item {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
            )
            {
                Column()
                {
                    TitleBlock(
                        navController = navController,
                        text = stringResource(R.string.workouts_my),
                        isIconVisible = true,
                        //iconResId =  iconResId
                    )
                    val yourTrains = trainList
                        ._workoutListGeneral
                        .observeAsState(listOf())
                    val yourExercises = trainList
                        .exerciseListGeneral
                        .observeAsState(listOf())

                    if ((yourTrains.value.size !=0 ) && (yourExercises.value.size !=0 ))
                    {
                    val trains =   yourTrains.value
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.87f)
                            .background(MaterialTheme.colorScheme.background),
                    ) {
                        items(trains)
                        {
                            val a = it
                            val b = it.isAddedToMyTrainList
                            // если помечена как "Моя тренировка"
                            if (it.isAddedToMyTrainList) {
                                WorkoutCard(
                                    workoutItem = it,
                                    onCardClickListener = {
                                    },
                                    // слушатели клика
                                    onAddedClickListener = {
                                        trainList.changeAddedStatusList(it)
                                    },
                                    onLikeClickListener = {
                                        trainList.changeLikedStatusList(it)
                                    },
                                    onPlayClickListener = {
                                    }
                                )
                            }
                        }
                        }
                    }
                }
            }
        }
    }
}
