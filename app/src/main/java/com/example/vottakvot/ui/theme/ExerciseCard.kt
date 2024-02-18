package com.example.vottakvot.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlaylistAdd
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.database.ExerciseDataItem
import com.example.vottakvot.navigation.screens.IconCloseButton
import com.example.vottakvot.utils.TextBlock

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun ExerciseCard(
    exerciseItem: ExerciseDataItem,
    isVisibleAddExercise: Boolean = false, // видно ли кнопку добавления
    onAddToMyTrain: (ExerciseDataItem) -> Unit, // при добавлении в мою текущую тренировку
    onExerciseClickListener: (ExerciseDataItem) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 8.dp,
                bottom = 8.dp
            )
            .height(70.dp),

    ) {
        Row(
            modifier = Modifier
                .padding(
                    top = 0.dp,
                    bottom = 0.dp,
                    start = 0.dp,
                    end = 0.dp
                )
                .clickable {
                    onExerciseClickListener(exerciseItem)
                }
                .animateContentSize()
        )
        {
            Row(
                modifier = Modifier
                    //.background(Color.Red)
                    .background(colorScheme.background)
                    .fillMaxWidth(0.25f)
            )
            {
                Gif(
                    gifUrl = exerciseItem.url,
                    modifier = Modifier
                        .width(150.dp)
                        .height(100.dp)
                    //.padding(16.dp)
                    // This `Column` animates its size when its content changes.
                    //.animateContentSize()
                )
            }

            /*Gif(
                gifUrl = exersiceItem.url,
                modifier = Modifier
                    //.fillMaxHeight(0.5f)
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(1f)
            )*/
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    //.background(Color.Gray)
                    .height(150.dp)
                    .padding(
                        start = 8.dp
                    ),
            )
            {
                TextBlock(
                    modifier = Modifier
                        //.background(Color.Green),
                            .padding(8.dp),
                    text = exerciseItem.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textColor = colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(4.dp))
                TextBlock(
                    modifier = Modifier
                        .background(Color.Blue),
                    // .padding(8.dp),
                    text = exerciseItem.approaches.toString() + " подхода",
                    // + exersiceItem.repetitions.toString() + " повторений",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    textColor = colorScheme.onBackground
                )
            }
                Column(
                    modifier = Modifier
                        .weight(0.2f)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Top
                )
                {
                    // если видно кнопку  "Добавить в Мои"
                    if (isVisibleAddExercise) {
                        IconCloseButton(
                            modifier = Modifier
                                //.background(Color.Yellow)
                                .size(
                                    35.dp
                                )
                                .padding(
                                    // top = 16.dp,
                                    //start = 8.dp
                                ),
                            iconResId = Icons.Outlined.PlaylistAdd,
                            iconResIdPressed = Icons.Outlined.PlaylistAdd,
                            isChanged = true
                        )
                        {
                            // добавить упражнение в мою текущую тренировку
                            onAddToMyTrain(exerciseItem)
                            //navController.popBackStack()
                        }
                    }
                }
            }
        }
    }


/*
@Preview
@Composable
fun ExerciseCardWhitePrev() {
    VotTakVotTheme(
        darkTheme = false)
    {
        //val generalViewModel =  GeneralViewModel()
        val trainList: TrainListViewModel =
            TrainListViewModel(source = sourceListTrainsForYouExample)
        val indEx = trainList.currentExerciseId
        val indWork = trainList.currentWorkoutId
        val exerciseItem = trainList.workoutListGeneral.value?.get(indWork)?.exersises?.get(indEx)
        if (exerciseItem != null) {
            ExerciseCard(
                exersiceItem = exerciseItem,
                onExerciseClickListener = {
                }
            )
        }
    }
}


@Preview
@Composable
fun ExerciseCardBlackPrev() {
    VotTakVotTheme(
        darkTheme = true)
    {
        //val generalViewModel =  GeneralViewModel()
        val trainList: TrainListViewModel =
            TrainListViewModel(source = sourceListTrainsForYouExample)
        val indEx = trainList.currentExerciseId
        val indWork = trainList.currentWorkoutId
        val exerciseItem = trainList.workoutListGeneral.value?.get(indWork)?.exersises?.get(indEx)
        if (exerciseItem != null) {
            ExerciseCard(
                exersiceItem = exerciseItem,
                onExerciseClickListener = {
                }
            )
        }
    }
}
*/