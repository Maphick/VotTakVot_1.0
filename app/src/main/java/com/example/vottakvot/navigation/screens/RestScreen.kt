package com.example.vottakvot.navigation.screens

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.database.ExerciseDataItem
import com.example.vottakvot.database.WorkoutDataItem
import com.example.vottakvot.navigation.navigationLogic.Screen
import com.example.vottakvot.ui.theme.Gif
import com.example.vottakvot.utils.HeaderBlock
import com.google.accompanist.pager.ExperimentalPagerApi

// Экран отдыха

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun RestScreen(
    navController: NavHostController,
    trainList: TrainListViewModel,
    workoutItem: WorkoutDataItem,
    //numberExercise: MutableState<Int>,// номер текущей тренировки
    //numberAproach: MutableState<Int> // номер текущего подхода
) {
    // чтобы возвращаться на экран, с которго было запущено упражнение
    //navController.popBackStack()
    // менялось ли название упражнения
    var exNameChanged = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    var numberExercisePlaying = trainList.currentExercisePlaying
    //var numberExercisePlaying = 0
    // текущий индекс проигрываемого подхода
    // все упражнения проиграны
    // переход на домашнюю страницу?
    if (numberExercisePlaying >= workoutItem.exerciseList.size) {
        /*Finish(
            navController = navController
        )*/
    } else {
        var currentExercise = ExerciseDataItem()
        try {
            currentExercise = workoutItem.exerciseList[numberExercisePlaying]
        } catch (e: Exception) {
            Log.d("EX", "numberExercise = " + numberExercisePlaying)
        }
        // текущий индекс проигрываемого подхода
        //var numberApproachPlaying = 0
        var numberApproachPlaying = trainList.currentApproachPlaying
        var currentTime = 0
        try {
            currentTime = currentExercise.repetitions.repetitions[numberApproachPlaying]
        } catch (e: Exception) {
            Log.d("EX", "numberApproachPlaying = " + numberApproachPlaying)
        }
        if (!exNameChanged.value) {
            if (numberApproachPlaying > currentExercise.repetitions.repetitions.size - 1) {
                //numberExercisePlaying++
                //numberApproachPlaying = 0
                trainList.currentExercisePlaying = numberExercisePlaying + 1
                trainList.currentApproachPlaying = 0
                exNameChanged.value = !exNameChanged.value
            }
        }
        //val numberExercise = 0
        //val numberAproach = 0
        // val workoutItem = WorkoutDataItem()
        // workoutItem.exerciseList = trainList.getExerciseListForOneWorkout(workoutItem.id)
        // текущее упражнение
        if (trainList.currentExercisePlaying >= workoutItem.exerciseList.size) {
            Finish(
                navController = navController
            )
        } else {
            var currentExercise = ExerciseDataItem()
            //  текущий индекс проигрываемой тренировки
            var numberExercisePlaying = trainList.currentExercisePlaying
            try {
                currentExercise = workoutItem.exerciseList[numberExercisePlaying]
            } catch (e: Exception) {
                Log.d("EX", "numberExercise = " + numberExercisePlaying)
            }
            var isPlaying = remember {
                mutableStateOf(true)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                HeaderBlock(
                    text = "",
                    navController = navController,
                    iconResId = Icons.Rounded.VolumeUp,
                    isVisibleAddTrain = true,
                    isPlay = true
                ) {}
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .padding(
                                8.dp
                            ),
                        horizontalArrangement = Arrangement.Center
                    )
                    {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(

                                ),
                            text = "Отдых. Далее:",
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 45.sp,
                            //fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            lineHeight = 45.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            //.height(.dp)
                            .fillMaxWidth(0.6f)
                            .padding(
                            ),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier
                                //.height(.dp)
                                .weight(0.9f)
                                .padding(
                                ),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(
                                    ),
                                text = currentExercise.title,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontSize = 26.sp,
                                maxLines = 3,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center,
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Row(
                            modifier = Modifier
                                .weight(0.1f)
                                .padding(
                                ),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            QuestionButton(
                                navController = navController,
                                trainList = trainList,
                                workoutItem = workoutItem,
                            ) {

                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(60.dp))
                    Row(
                        // color =  MaterialTheme.colorScheme.background,
                        modifier = Modifier
                            //.background(Color.Green)
                            .fillMaxWidth()
                            .height(120.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .weight(0.4f),
                            horizontalArrangement = Arrangement.End
                        ) {

                        }
                        Row(
                            modifier = Modifier
                                //.background(Color.Green)
                                .weight(0.3f),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Box(
                                contentAlignment = Alignment.Center
                            ) {
                                //trainList.currentExercisePlaying = 0
                                //trainList.currentApproachPlaying = 0
                                Timer(
                                    trainList = trainList,
                                    navController = navController,
                                    isPlaying = isPlaying,
                                    totalTime = 10L * 1000L,
                                    handleColor = MaterialTheme.colorScheme.primary,
                                    inactiveBarColor = MaterialTheme.colorScheme.background,
                                    activeBarColor = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        //.background(Color.Yellow)
                                        .size(120.dp)
                                )
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                //.background(Color.Yellow)
                                .weight(0.4f),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // пропустить приготовление к упражнению
                            SkipButton(
                                text = "Пропустить"
                            ) {
                                // переход на страницу выполнения упражнени
                                navController.navigate(Screen.Doing.route)
                            }
                        }
                    }
                }
            }
        }
    }
}

