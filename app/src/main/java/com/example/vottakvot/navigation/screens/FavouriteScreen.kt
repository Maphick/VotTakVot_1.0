package com.example.vottakvot.navigation.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.navigation.navigationLogic.Screen
import com.example.vottakvot.ui.theme.VotTakVotTheme
import com.example.vottakvot.ui.theme.WorkoutCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

@Composable
fun FavouriteScreen(
    navController: NavHostController,
    trainList: TrainListViewModel,
    //workoutItem:  WorkoutDataItem
    ) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f)
            .background(MaterialTheme.colorScheme.background),

        ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        )
        {
            Column(
                modifier = Modifier
                    //.background(Color.Green)
                    .fillMaxWidth()
                    .fillMaxHeight(0.87f)
            )
            {
                TitleBlock(
                    navController = navController,
                    text = stringResource(R.string.favourite),
                    isIconVisible = false,
                    //iconResId =  iconResId
                )
                val yourTrains = trainList
                    .workoutListGeneral
                    .observeAsState(listOf())
                val yourExercises = trainList
                    .exerciseListGeneral
                    .observeAsState(listOf())

                if ((yourTrains.value != null) && (yourTrains.value.size != 0)) {
                    val trains = yourTrains.value
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(1f)
                            .background(MaterialTheme.colorScheme.background),
                    ) {
                        // список Моих тренировок
                        items(trains, key = { it.id })
                        {
                            var workoutItem = it
                            // если тренировка добавлена в Избранное
                            if (workoutItem.isAddedToFavourite)
                            WorkoutCard(
                                navController = navController,
                                trainList = trainList,
                                //workoutItem = trainList.findWorkoutById(trainList.currentWorkoutId),
                                workoutItem = workoutItem,
                                // клик по карточке
                                onCardClickListener = {
                                    GlobalScope.launch {
                                        withContext(Dispatchers.Main) {
                                            trainList.currentWorkoutId = it.id
                                            navController.navigate(Screen.Workout.route)
                                        }

                                    }
                                },
                                // при добавлении в мои тренировки
                                onAddedClickListener = {
                                    // если тренировки нет в списке моих тренировок
                                    if (!it.isAddedToMyTrainList) {
                                        // создание новой Моей Тренировки
                                        val newMyTrain = it.copy()
                                        // добавляем в мои тренировки как новую тренировку
                                        newMyTrain.id = UUID.randomUUID().toString()
                                        //nextValue()
                                        newMyTrain.title = "My " + newMyTrain.title
                                        newMyTrain.isAddedToMyTrainList = true
                                        //  добавление упражнений в тренировку
                                        val allExercises = trainList.getExerciseListForOneWorkout(it.id)
                                        // добавить упражнения к этой тренировке
                                        for (exercise in allExercises)
                                        {
                                            var ex = exercise.copy(
                                                id = UUID.randomUUID().toString(),
                                                //nextValue(),
                                                workoutId = newMyTrain.id
                                            )
                                            newMyTrain.exerciseList.add(ex)
                                        }
                                        trainList.insertOneWorkoutWithExercise(newMyTrain)
                                        Toast.makeText(context,      it.title + " была добавлена в Мои Тренировки", Toast.LENGTH_SHORT).show()

                                    }
                                    else
                                    {
                                        //trainList.changeAddedStatusList(it)
                                    }
                                    //trainList.changeAddedStatusList(it)
                                    //trainListMy.changeAddedStatusList(it)
                                },
                                onLikeClickListener = {
                                    //it.isAddedToFavourite= true
                                    trainList.changeLikedStatusList(it)
                                    //trainListFavourite.changeLikedStatusList(it)
                                },
                                onPlayClickListener = {
                                    //trainListForYou.changePlayingStatusList(it)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}


                /*
@Preview
@Composable
fun FavouriteScreenWhitePrev() {
    VotTakVotTheme(
        darkTheme = false)
    {
        FavouriteScreen()
    }
}

@Preview
@Composable
fun FavouriteScreenBlackPrev() {
    VotTakVotTheme(
        darkTheme = true)
    {
        FavouriteScreen()
    }
}
                */