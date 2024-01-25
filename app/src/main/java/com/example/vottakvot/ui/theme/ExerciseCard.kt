package com.example.vottakvot.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.database.ExerciseDataItem
import com.example.vottakvot.utils.TextBlock
import sourceListTrainsForYouExample

@Composable
fun ExerciseCard(
    exersiceItem: ExerciseDataItem,
    onExerciseClickListener: (ExerciseDataItem) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
               top = 8.dp,
                bottom = 8.dp
            )
            .height(50.dp),

    ) {
        Row(
            modifier = Modifier.padding(
                top = 0.dp,
                bottom = 0.dp,
                start = 0.dp,
                end = 0.dp
            )
                .clickable {
                    onExerciseClickListener(exersiceItem)
                }
        )
        {
            Image(
                painter = painterResource(id = exersiceItem.img),
                contentDescription = "Workout Screen",
                modifier = Modifier,
                //   .size(
                //      50.dp
                //   )
                // .fillMaxWidth(0.5f)
                //.height(150.dp)

                // .fillMaxWidth(1.2f)
            )
            Column(
                modifier = Modifier
                    .padding(
                        start = 8.dp),
            )
            {
                TextBlock(
                    modifier = Modifier,
                    // .padding(8.dp),
                    text = exersiceItem.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textColor = colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(4.dp))
                TextBlock(
                    modifier = Modifier,
                    // .padding(8.dp),
                    text = exersiceItem.approaches.toString() + " подхода | " +
                            exersiceItem.repetitions.toString() + " повторений",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    textColor = colorScheme.onBackground
                )
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