package com.example.vottakvot.navigation.screens


import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.Button
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.database.WorkoutDataItem
import com.example.vottakvot.navigation.navigationLogic.Screen
import com.example.vottakvot.ui.theme.Gif
import com.example.vottakvot.utils.HeaderBlock
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.delay
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


// экран подготовки к упражнению
@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun PreparationScreen(
    navController: NavHostController,
    trainList: TrainListViewModel,
    workoutItem: WorkoutDataItem,
    //numberExercise: MutableState<Int>,// номер текущей тренировки
    //numberAproach: MutableState<Int> // номер текущего подхода
) {

    val context = LocalContext.current
    val numberExercise = 0
    val numberAproach = 0
   // val workoutItem = WorkoutDataItem()
   // workoutItem.exerciseList = trainList.getExerciseListForOneWorkout(workoutItem.id)
    // если в тренировке нет упражнений
    // текущее упражнение
    if (workoutItem.exerciseList.size == 0)
    {
        Toast.makeText(context,    "В тренировке" + workoutItem.title + " ещё нет упражнений. Добавьте хотя бы одно упражнение.", Toast.LENGTH_LONG).show()
        //return
    }
    val currentExercise = workoutItem.exerciseList[numberExercise]
    // время выполнения текущего упражнения
    val currentTime = currentExercise.repetitions.repetitions[numberAproach]

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

           )
        {

        }
        Spacer(modifier = Modifier.height(10.dp))
        val gifurl = "https://v2.exercisedb.io/image/ozgMekY0bIcGND"
        if (isPlaying.value)
        {
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
                .height(300.dp)
            )
        }
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
                text = "Приготовьтесь!",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 45.sp,
                //fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                lineHeight = 25.sp
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
                    //lineHeight = 25.sp
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
                    workoutItem = workoutItem
                ) {

                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
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
                        trainList.currentExercisePlaying = 0
                        trainList.currentApproachPlaying = 0
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





// Кнопка пропуска экрана подготовки к тренировке
@Composable
fun SkipButton(
    isDoingNow: Boolean = false,
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 14.sp,
    iconSize: Dp = 35.dp,
    iconDoingSize: Dp = 55.dp,
    textColor: Color = colorScheme.primary,
    onClick: () -> Unit
) {
    androidx.compose.material3.OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            //.background(Color.Green)
            //.width(50.dp)
            .height(50.dp),

        onClick = {
            onClick()
        },
        border = BorderStroke(1.dp, Color.Transparent),
        //shape = RoundedCornerShape(50), // = 50% percent
        // or shape = CircleShape

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(60.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            if (!isDoingNow) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.85f),
                        //.height(60.dp)
                        //.background(Color.Red),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = text,
                        textAlign = TextAlign.Start,
                        fontSize = fontSize,
                        maxLines = 1,
                        color = textColor
                    )
                }
                //Spacer(modifier = Modifier.width(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(1f),
                        //.height(60.dp)
                        //.background(Color.Gray),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    //Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        modifier = Modifier
                            //.background(Color.Green)
                            .size(iconSize),
                        imageVector = Icons.Default.ArrowForwardIos,
                        tint = textColor,
                        contentDescription = null
                    )
                }
            }
            else
            {

            }
        }
    }
}

// редактирование тренировок
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun QuestionButton(
    navController: NavHostController,
    trainList: TrainListViewModel,
    workoutItem: WorkoutDataItem,
    imageVector: ImageVector = Icons.Default.QuestionMark,
    onClick: () -> Unit
) {
    // чтобы возвращаться на экран, с которго было запущено упражнение
    Box()
    {
        OutlinedButton(
            modifier = Modifier
                //.background(Color.Blue)
                .width(20.dp)
                .height(20.dp)
                .padding(
                ),
            onClick =
            {
                // переход к странице информации об упражнении
                val numberExercise = trainList.currentExerciseId
                val currentExercise = workoutItem.exerciseList[trainList.currentExercisePlaying]
                trainList.currentExerciseId = currentExercise.id
                navController.navigate(Screen.Exercise.route)
                Log.d("EDIT", "Go to  Exercise Screen")
                onClick()
            },
            shape = CircleShape,
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent
               // MaterialTheme.colorScheme.primary
            )
        ) {

        }
        Row(
            modifier = Modifier
                .padding(
                    //start = 4.dp,
                    //top = 4.dp
                )
                //.background(Color.Red)
                .width(20.dp)
                .height(20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Icon(
                modifier = Modifier
                    .height(15.dp)
                    .width(15.dp),
                imageVector = imageVector,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}





