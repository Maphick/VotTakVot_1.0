package com.example.vottakvot.navigation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.database.BodyType
import com.example.vottakvot.database.ExerciseDataItem
import com.example.vottakvot.ui.theme.Gif
import com.example.vottakvot.ui.theme.TimeAndBodyPart
import com.example.vottakvot.ui.theme.VotTakVotTheme
import com.example.vottakvot.utils.HeaderBlock
import com.example.vottakvot.utils.TextBlock


@Composable
fun ExerciseScreen(
    navController: NavHostController,
    trainList: TrainListViewModel,
    isVisibleAddExercise: Boolean = false, // видно ли кнопку добавления
    //onAddToMyTrain: (ExerciseDataItem) -> Unit, // при добавлении в мою текущую тренировку
    ) {

    val workoutIndex = trainList.currentWorkoutId
    val exerciseIndex = trainList.currentExerciseId

    var workoutItem = trainList.findWorkoutById(workoutIndex)
    var exerciseItem = trainList.findExerciseById(exerciseIndex)



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )
    {
        HeaderBlock(
            text = "Упражнение",
            navController = navController,
            isVisibleAddTrain  = isVisibleAddExercise, //  видно ли кнопку добавления тренировки
        )
        {
            //  Добавить упражнение в свои?
            trainList.addExerciseToWorkout(workoutItem, exerciseItem)
        }
        // Порядок выполнения
        LazyColumn(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,

                    )
                .fillMaxWidth(1f)
                .fillMaxHeight(1f)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        )
        {
            item {
                if (exerciseItem != null) {
                    LazyColumnExerciseItem(
                        navController = navController,
                        exerciseItem = exerciseItem
                    )
                    for (i in 0..exerciseItem.instructionList.instructions.size-1) {
                    Row(
                        modifier = Modifier
                            //.height(30.dp)
                            .padding(
                                top = 8.dp,
                                start = 16.dp
                            )
                    )
                    {
                        val instruction = exerciseItem.instructionList.instructions[i]
                        Text(
                            text = i.toString(),
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        TextBlock(
                            text = instruction,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                    }
                }
            }
            item {
                // Противопоказания
                Spacer(modifier = Modifier.height(24.dp))
                TextBlock(
                    text = stringResource(R.string.workout_contraindications),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textColor = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextBlock(
                    text = exerciseItem.contraindications,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    textColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun  LazyColumnExerciseItem(
    navController: NavHostController,
    exerciseItem: ExerciseDataItem
)
{
    val instructionsCount = exerciseItem.instructionList.instructions.size
    /*Image(
        painter = painterResource(id = R.drawable.exercise),
        contentDescription = "Exercise Screen",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )*/
    Row(
        modifier = Modifier
            //.background(Color.Red)
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth(1f)
    )
    {
        Gif(
            gifUrl = exerciseItem.url,
            //modifier = Modifier
                //.width(150.dp)
                //.height(100.dp)
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
    if (exerciseItem != null) {
        TextBlock(
            text = "${exerciseItem.title}",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textColor = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))
        val bodyType =  when (exerciseItem.bodyType) {
            "ABD" ->  BodyType.ABD
            "UPPER_BODY" ->  BodyType.UPPER_BODY
            "BOTTOM_BODY" ->  BodyType.BOTTOM_BODY
            "FULL_BODY" ->  BodyType.FULL_BODY
            else -> {
                BodyType.FULL_BODY
            }
        }
        TimeAndBodyPart(
            modifier = Modifier
                .fillMaxWidth(1f),
            time = exerciseItem.time,
            bodyType = bodyType,
        )
        Spacer(modifier = Modifier.height(24.dp))
        TextBlock(
            text = stringResource(R.string.steps),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textColor = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}


@Preview
@Composable
fun ExerciseScreenWhitePrev() {
    VotTakVotTheme(
        darkTheme = false)
    {
       // ExerciseScreen(

      //  )
    }
}


/*
@Preview
@Composable
fun ExerciseScreenBlackPrev() {
    VotTakVotTheme(
        darkTheme = true)
    {
        val context = LocalContext.current
        val trainListForYou: TrainListViewModel =
            TrainListViewModel(
                source = sourceListTrainsForYouExample,
                db
            )
        val trainListPopular: TrainListViewModel =
            TrainListViewModel(source = sourceListPopularExample)
        val navController = NavHostController(context = context)
        ExerciseScreen(
            navController = navController,
            trainList = trainListForYou
        )
    }
}
*/