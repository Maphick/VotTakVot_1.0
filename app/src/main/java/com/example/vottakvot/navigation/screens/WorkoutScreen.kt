package com.example.vottakvot.navigation.screens

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.vottakvot.database.BodyType
import com.example.vottakvot.database.WorkoutDataItem
import com.example.vottakvot.navigation.navigationLogic.Screen
import com.example.vottakvot.ui.theme.ExerciseCard
import com.example.vottakvot.ui.theme.InfoIconWithText
import com.example.vottakvot.ui.theme.TimeAndBodyPart
import com.example.vottakvot.ui.theme.VotTakVotTheme
import com.example.vottakvot.utils.HeaderBlock
import com.example.vottakvot.utils.TextBlock
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class
)
@Composable
fun WorkoutScreen(
    //typeList: Int = 0,
    navController: NavHostController,
    trainList: TrainListViewModel,
    workoutItem: WorkoutDataItem
) {
            val context = LocalContext.current
            workoutItem.exerciseList = trainList.getExerciseListForOneWorkout(workoutItem.id)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            )
            {
                HeaderBlock(
                    text = "Тренировка",
                    navController = navController,
                    isVisibleAddTrain = true // видно кнопку "добавить в мои"
                )
                {
                    //Добавить тренировку в мои
                    workoutItem.isAddedToMyTrainList = true
                    trainList.updateWorkout(workoutItem)
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                )
                {

                LazyColumn(
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp,
                        )
                        .fillMaxWidth(1f)
                        .fillMaxHeight(0.9f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                )
                {
                    // для свайпа влево
                    item {
                        if (workoutItem != null) {
                            LazyColumnWorkoutItem(
                                navController = navController,
                                workoutItem = workoutItem
                            )
                            for (exercise in workoutItem.exerciseList) {
                                ExerciseCard(
                                    exerciseItem = exercise,
                                    onAddToMyTrain = {},
                                    onExerciseClickListener = {
                                        trainList.currentExerciseId = it.id
                                        GlobalScope.launch {
                                            withContext(Dispatchers.Main) {
                                                trainList.currentExerciseId = it.id
                                                navController.navigate(Screen.Exercise.route)
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    )
                    {
                        // запуск тренировки
                        StartButton(
                            modifier = Modifier
                                //.background(Color.Black)
                                .padding(
                                    bottom = 20.dp
                                )
                                .fillMaxWidth(1f)
                                .height(50.dp),
                            text = stringResource(R.string.start),
                        ) {
                            trainList.currentWorkoutId = workoutItem.id
                            if (workoutItem.exerciseList.size == 0)
                            {

                                Toast.makeText(context,    "В тренировке " + workoutItem.title + " ещё нет упражнений. Добавьте хотя бы одно упражнение.", Toast.LENGTH_LONG).show()
                            }
                            else
                            // переход на страницу подготовки к проигрыванию тренировки
                            navController.navigate(Screen.Preparation.route)
                        }
                    }
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
        shape =  RoundedCornerShape(10.dp),
        //border= BorderStroke(1.dp, Color.Blue),
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.background,
            backgroundColor = MaterialTheme.colorScheme.primary
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
                text = text,
                colorText = MaterialTheme.colorScheme.background,
                colorIcon = MaterialTheme.colorScheme.background
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
    val exerciseCount = workoutItem.exerciseList.size
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
        val bodyType =  when (workoutItem.bodyType) {
            "Пресс" ->  BodyType.ABD
            "Верхняя часть" ->  BodyType.UPPER_BODY
            "Нижняя часть" ->  BodyType.BOTTOM_BODY
            "Все тело" ->  BodyType.FULL_BODY
            else -> {
                BodyType.FULL_BODY
            }
        }
        TimeAndBodyPart(
            modifier = Modifier
                .fillMaxWidth(1f),
            time = workoutItem.time,
            bodyType = bodyType
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


/*
@Preview
@Composable
fun WorkoutScreenWhitePrev() {
    VotTakVotTheme(
        darkTheme = false)
    {
        val context = LocalContext.current
        // val trainListForYou: TrainListViewModel =
        //     TrainListViewModel(source = sourceListTrainsForYouExample)
        // val trainListPopular: TrainListViewModel =
        //     TrainListViewModel(source = sourceListPopularExample)
        val navController = NavHostController(context = context)
        WorkoutScreen(
            navController = navController,
            workoutItem = WorkoutDataItem(),
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
       // val trainListForYou: TrainListViewModel =
       //     TrainListViewModel(source = sourceListTrainsForYouExample)
       // val trainListPopular: TrainListViewModel =
       //     TrainListViewModel(source = sourceListPopularExample)
        val navController = NavHostController(context = context)
        WorkoutScreen(
            navController = navController,
            workoutItem = WorkoutDataItem()
            //trainList = trainListForYou
        )
    }
}
*/