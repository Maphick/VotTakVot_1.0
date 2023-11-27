package com.example.vottakvot.navigation.screens

import android.graphics.drawable.Icon
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.PlaylistAdd
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.domain.WorkoutDataItem
import com.example.vottakvot.navigation.navigationLogic.Screen
import com.example.vottakvot.ui.theme.ExerciseCard
import com.example.vottakvot.ui.theme.InfoIconWithText
import com.example.vottakvot.ui.theme.TimeAndBodyPart
import com.example.vottakvot.ui.theme.VotTakVotTheme
import com.example.vottakvot.utils.HeaderBlock
import com.example.vottakvot.utils.TextBlock
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import sourceListPopularExample
import sourceListTrainsForYouExample

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun WorkoutScreen(
    navController: NavHostController,
    trainList: TrainListViewModel,
) {
    val workoutIndex = trainList.currentWorkoutId
    var workoutItem = trainList.findWorkoutById(workoutIndex)
        //trainList.workoutListGeneral.value?.get(0)
    Column(
        modifier = Modifier.fillMaxWidth()
            .fillMaxHeight()
    )
    {
    HeaderBlock(
        text = "Тренировка",
        navController = navController
    )
        LazyColumn(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,

                    )
                .fillMaxWidth(1f)
                .fillMaxHeight(0.9f)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            item {

                if (workoutItem != null) {
                    LazyColumnWorkoutItem(
                        navController = navController,
                        workoutItem = workoutItem
                    )
                    for (exercise in workoutItem.exersises) {
                        ExerciseCard(
                            exersiceItem = exercise,
                            onExerciseClickListener = {

                                trainList.currentExerciseId = it.id
                                // переход на страницу упражнения
                                navController.navigate(Screen.Exercise.route)

                            }
                        )
                    }
                }
            }
        }
        StartButton(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(50.dp),
            text = stringResource(R.string.start),
        ) {

        }
    }

}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun StartButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(
                start = 8.dp,
                end = 16.dp,
                //bottom = 16.dp
            ),
        onClick =
        {
            onClick()
        },
        shape = CircleShape,
        //border= BorderStroke(1.dp, Color.Blue),
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.surface,
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Row(
           modifier = Modifier
               .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        )
        {
            InfoIconWithText(
                modifier = Modifier,
                iconResId = Icons.Filled.PlayArrow,
                text = text
            )
        }
    }
}



@Composable
fun  LazyColumnWorkoutItem(
    navController: NavHostController,
    workoutItem: WorkoutDataItem
)
{
    val exerciseCount = workoutItem.exersises.size
    //Spacer(modifier = Modifier.height(24.dp))
    Image(
        painter = painterResource(id = R.drawable.workout),
        contentDescription = "Workout Screen",
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)

        // .fillMaxWidth(1.2f)
    )
    Spacer(modifier = Modifier.height(16.dp))
    if (workoutItem != null) {
        TextBlock(
            text = "${workoutItem.title}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textColor = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))
        TimeAndBodyPart(
            modifier = Modifier
                .fillMaxWidth(1f),
            time = workoutItem.time,
            bodyType = workoutItem.bodyType
        )
        Spacer(modifier = Modifier.height(24.dp))
        TextBlock(
            text = stringResource(R.string.workout_description),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textColor = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextBlock(
            text = workoutItem.description,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(24.dp))
        TextBlock(
            text = stringResource(R.string.workout_contraindications),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textColor = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextBlock(
            text = workoutItem.contraindications,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(24.dp))
        TextBlock(
            text = stringResource(R.string.exercises) + exerciseCount,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textColor = MaterialTheme.colorScheme.onBackground
        )
    }
}


@Preview
@Composable
fun WorkoutScreenWhitePrev() {
    VotTakVotTheme(
        darkTheme = false)
    {
        val context = LocalContext.current
        val trainListForYou: TrainListViewModel =
            TrainListViewModel(source = sourceListTrainsForYouExample)
        val trainListPopular: TrainListViewModel =
            TrainListViewModel(source = sourceListPopularExample)
        val navController = NavHostController(context = context)
        WorkoutScreen(
            navController = navController,
            trainList = trainListForYou
        )
    }
}

@Preview
@Composable
fun WorkoutScreenScreenBlackPrev() {
    VotTakVotTheme(
        darkTheme = true)
    {
        val context = LocalContext.current
        val trainListForYou: TrainListViewModel =
            TrainListViewModel(source = sourceListTrainsForYouExample)
        val trainListPopular: TrainListViewModel =
            TrainListViewModel(source = sourceListPopularExample)
        val navController = NavHostController(context = context)
        WorkoutScreen(
            navController = navController,
            trainList = trainListForYou
        )
    }
}