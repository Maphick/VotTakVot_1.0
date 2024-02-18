package com.example.vottakvot.navigation.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.rounded.AccessAlarm
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.database.ExerciseDataItem
import com.example.vottakvot.database.WorkoutDataItem
import com.example.vottakvot.navigation.navigationLogic.Screen
import com.example.vottakvot.ui.theme.Gif
import com.example.vottakvot.ui.theme.VotTakVotTheme
import com.example.vottakvot.utils.HeaderBlock
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


// Экран выполнения упражнения
            @OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
            @RequiresApi(Build.VERSION_CODES.P)
            @Composable
            fun DoingScreen(
                navController: NavHostController,
                trainList: TrainListViewModel,
                workoutItem: WorkoutDataItem,
            ) {
    // чтобы возвращаться на экран, с которго было запущено упражнение
    //navController.popBackStack()
    val context = LocalContext.current
    //  текущий индекс проигрываемой тренировки
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
        }
        catch (e: Exception)
        {
            Log.d("EX", "numberExercise = " +  numberExercisePlaying)
        }
        // текущее упражнение

        // текущий индекс проигрываемого подхода
        //var numberApproachPlaying = 0
        var numberApproachPlaying = trainList.currentApproachPlaying
            var currentTime = 0
            try {
                currentTime = currentExercise.repetitions.repetitions[numberApproachPlaying]
            }
            catch (e: Exception)
            {
                Log.d("EX", "numberApproachPlaying = " +  numberApproachPlaying)
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
                )
                {

                }
                Spacer(modifier = Modifier.height(8.dp))
                if (isPlaying.value) {
                    val a = ""
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                )
                {
                    Gif(
                        gifUrl = currentExercise.url,
                        modifier = Modifier
                            .height(400.dp)
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )
                {
                    Row(
                        modifier = Modifier
                            //.background(Color.Green)
                            .weight(0.3f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Timer(
                                isDoingNow = true, // идет проигрывание тренировки
                                trainList = trainList,
                                navController = navController,
                                isPlaying = isPlaying,
                                totalTime = currentTime * 1000L,
                                handleColor = MaterialTheme.colorScheme.primary,
                                inactiveBarColor = MaterialTheme.colorScheme.background,
                                activeBarColor = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .size(120.dp),
                                skipTo =
                                // если это последнее упражнение - переход к домашнему экрану
                                if (numberExercisePlaying == workoutItem.exerciseList.size ) {
                                    Screen.Home.route
                                }
                                // если нет - к экрану отдыха
                                else {
                                    Screen.Rest.route
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                // название следующей тренировки
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
                            .weight(0.95f)
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
                            //lineHeight = 25.sp
                        )
                    }
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
                            workoutItem = workoutItem
                        ) {

                        }
                    }
                }
            }
        }

    }


@Composable
fun Timer(
    isDoingNow: Boolean = false,
    iconDoingSize: Dp = 55.dp,
    textColor: Color = MaterialTheme.colorScheme.primary,
    trainList: TrainListViewModel,
    navController: NavHostController,
    isPlaying: MutableState<Boolean>,
    totalTime: Long,
    handleColor: Color,
    inactiveBarColor: Color,
    activeBarColor: Color,
    modifier: Modifier = Modifier,
    initialValue: Float = 1f,
    strokeWidth: Dp = 5.dp,
    skipTo: String = Screen.Doing.route
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    var value by remember {
        mutableStateOf(initialValue)
    }
    var currentTime by remember {
        mutableStateOf(totalTime)
    }
    var isTimerRunning by remember {
        mutableStateOf(isPlaying.value)
    }
    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if (currentTime > 0 && isTimerRunning) {
            delay(100L)
            currentTime -= 100L
            value = currentTime / totalTime.toFloat()
        }
        // когда время истекло
        if (currentTime == 0L) {
            // переход на страницу выполнения упражнени
            //trainList.currentExercisePlaying = 0
            trainList.currentApproachPlaying++
            navController.navigate(skipTo)
        }
    }
    // пауза
    Row()
    {
        // экран проигрывания тренировки
        if (isDoingNow) {
            Row(
                modifier = modifier
                    .clip(CircleShape)
                    .clickable {
                        if (currentTime <= 0L) {
                            currentTime = totalTime
                            isTimerRunning = true
                            //isPlaying.value = true
                        } else {
                            isTimerRunning = !isTimerRunning
                            //isPlaying.value = false
                        }
                    }
                    .weight(0.3f),
                //.height(60.dp)
                //.background(Color.Gray),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            )
            {
                //Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        //.background(Color.Green)
                        .size(iconDoingSize),
                    imageVector = Icons.Default.Pause,
                    tint = textColor,
                    contentDescription = null
                )
            }
        }


        /*Button(
                    onClick = {
                        if (currentTime <= 0L) {
                            currentTime = totalTime
                            isTimerRunning = true
                            isPlaying.value = true
                        } else {
                            isTimerRunning = !isTimerRunning
                            isPlaying.value = false
                        }
                    },
                    modifier = Modifier.align(Alignment.Top),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if (!isTimerRunning || currentTime <= 0L) {
                            Color.Green
                        } else {
                            Color.Red
                        }
                    )
                ) {
                    Text(
                        text = if (isTimerRunning && currentTime >= 0L) "Stop"
                        else if (!isTimerRunning && currentTime >= 0L) "Start"
                        else "Restart"
                    )
                }*/
        Row(
            modifier = Modifier
                .weight(0.3f)
                .height(160.dp),
            //.background(Color.Gray),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .onSizeChanged {
                        size = it
                    }
            ) {
                Canvas(modifier = modifier) {
                    drawArc(
                        color = inactiveBarColor,
                        startAngle = 90f,
                        sweepAngle = 360f,
                        useCenter = false,
                        size = Size(size.width.toFloat(), size.height.toFloat()),
                        style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                    )
                    drawArc(
                        color = activeBarColor,
                        startAngle = 90f,
                        sweepAngle = 360f * value - 0,
                        useCenter = false,
                        size = Size(size.width.toFloat(), size.height.toFloat()),
                        style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                    )
                    val center = Offset(size.width / 2f, size.height / 2f)
                    val beta = (360f * value + 90f) * (PI / 180f).toFloat()
                    val r = size.width / 2f
                    val a = cos(beta) * r
                    val b = sin(beta) * r
                    /* drawPoints(
                listOf(Offset(center.x + a, center.y + b)),
                pointMode = PointMode.Points,
                color = handleColor,
                strokeWidth = (strokeWidth * 3f).toPx(),
                cap = StrokeCap.Round
            )*/
                }
                Text(
                    text = (currentTime / 1000L).toString(),
                    fontSize = 44.sp,
                    //fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        // экран проигрывания тренировки
        if (isDoingNow) {
            Row(
                modifier = Modifier
                    .clickable {

                        // переход на страницу с отдыхом
                        //trainList.currentExercisePlaying = 0
                        //trainList.currentApproachPlaying++
                        //navController.navigate(Screen.Rest.route)
                    }
                    .weight(0.3f),
                //.height(60.dp)
                //.background(Color.Gray),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Row(
                    modifier = modifier
                        .clip(CircleShape)
                        .clickable {
                            // переход на страницу с отдыхом
                            trainList.currentApproachPlaying++
                            navController.navigate(skipTo)
                            //++trainList.currentApproachPlaying
                            //navController.navigate(Screen.Rest.route)
                        },
                    //.fillMaxWidth(1f)
                    //.height(60.dp)
                    //.background(Color.Gray),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    //Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        modifier = Modifier
                            .clip(CircleShape)
                            //.background(Color.Green)
                            .size(iconDoingSize),
                        imageVector = Icons.Default.ArrowForwardIos,
                        tint = textColor,
                        contentDescription = null
                    )
                }
            }
        }
    }
}


// окончание тренировки
@Composable
fun Finish(
    navController: NavHostController
)
{
        //  тренировка окончена
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
            )
            {

            }
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
                        text = "Тренировка окончена!",
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 45.sp,
                        //fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        lineHeight = 45.sp
                    )
                }
            }
        }
    }


/*
@RequiresApi(Build.VERSION_CODES.P)
@Preview
@Composable
fun WorkoutScreenScreenBlackPrev() {
    VotTakVotTheme(
        darkTheme = false)
    {
        val context = LocalContext.current
        val navController = NavHostController(context = context)
        DoingScreen(
            navController = navController
        )
    }
}
*/