package com.example.vottakvot.ui.theme

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.PlaylistAdd
import androidx.compose.material.icons.sharp.PlayCircleFilled
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.InquirerViewModel
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.database.BodyType
import com.example.vottakvot.database.WorkoutDataItem
import com.example.vottakvot.navigation.navigationLogic.Screen
import com.example.vottakvot.navigation.screens.FindYoursWorkouts
import com.example.vottakvot.navigation.screens.LazyColumnExerciseItem
import com.example.vottakvot.navigation.screens.SearchWorkoutsButton
import com.example.vottakvot.utils.TextBlock
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun MyWorkoutCard(
    navController: NavHostController,
    workoutItem: WorkoutDataItem,
    trainListForYou: TrainListViewModel,
    onCardClickListener: (WorkoutDataItem) -> Unit,
    onPlayClickListener: (WorkoutDataItem) -> Unit,
    onEditClickListener: (WorkoutDataItem) -> Unit,
) {
    // карточка тренировки
    Card(
        modifier = Modifier
            .clickable {
                onCardClickListener(workoutItem)
            }
            .fillMaxWidth()
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 8.dp
            ),
            //.height(140.dp),
        backgroundColor = colorScheme.surface,
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
            bottomStart = 16.dp,
            bottomEnd = 16.dp
        ),
        border = BorderStroke(1.dp, colorScheme.onBackground)
    ) {
        Column(
            modifier = Modifier.padding(
                top = 16.dp,
                bottom = 16.dp,
                start = 16.dp,
                end = 16.dp
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
                Row(
                    modifier = Modifier
                        .padding(
                            start = 8.dp
                        )
                        .fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "${workoutItem.title}",
                        fontSize = 20.sp,
                        color = colorScheme.primary,
                    )
                }
            //Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    //.background(Color.Gray)
                    .fillMaxWidth(1f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            )
            {
                Column(
                    modifier = Modifier
                        .padding(
                            top = 8.dp,
                            start = 8.dp
                        )
                        .fillMaxWidth(0.7f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    val bodyType = when (workoutItem.bodyType) {
                        "ABD" -> BodyType.ABD
                        "UPPER_BODY" -> BodyType.UPPER_BODY
                        "BOTTOM_BODY" -> BodyType.BOTTOM_BODY
                        "FULL_BODY" -> BodyType.FULL_BODY
                        else -> {
                            BodyType.FULL_BODY
                        }
                    }
                    // иконки времени и части тела
                    TimeAndBodyPart(
                        time = workoutItem.time,
                        bodyType = bodyType,
                        isBodyPartVisible = false
                        //bodyType
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    ExerciseList(
                        workoutItem = workoutItem,
                        trainListForYou = trainListForYou
                    )
                }
                Column(
                    modifier = Modifier
                //.background(Color.Green),
                       // .size(60.dp),
                    //horizontalArrangement = Arrangement.End,
                    //verticalAlignment = Alignment.Top
                )
                {
                    //  переход в режим редактирования тренировки
                    EditWorkoutButton(
                        currentWorkout = workoutItem,
                        trainList = trainListForYou,
                    )
                    {
                        // переход на страницу редактирования моей тренировки
                        trainListForYou.currentWorkoutId = workoutItem.id
                        navController.navigate(Screen.EditOneTrain.route)
                    }
                    PlayWorkoutButton(
                        currentWorkout = workoutItem,
                        //trainList = trainListForYou,
                    ) {
                        onPlayClickListener(workoutItem)
                    }
                }
            }
        }
    }
}


// редактирование тренировок
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun EditWorkoutButton(
    //navController: NavHostController,
    //modifier: Modifier,
    trainList: TrainListViewModel,
    currentWorkout: WorkoutDataItem,
    //inquirerViewModel: InquirerViewModel,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = Modifier
            .width(200.dp)
            .height(60.dp)
            .padding(
                start = 0.dp,
                end = 0.dp,
                bottom = 0.dp,
                top = 8.dp
            ),
        onClick =
        {
            Log.d("EDIT", "Edit Yours Workouts")
            onClick()
        },
        shape = CircleShape,
        border= BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
            //MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier
                //background(Color.Red)
                .fillMaxWidth(1f)
            ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Icon(
                modifier = Modifier
                    .size(60.dp)
                    .padding(
                        //start = 8.dp
                    )
                ,
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

// найти тренировки
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun PlayWorkoutButton(
    //navController: NavHostController,
    //modifier: Modifier,
    //trainList: TrainListViewModel,
    currentWorkout: WorkoutDataItem,
    //inquirerViewModel: InquirerViewModel,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = Modifier
            .width(200.dp)
            .height(60.dp)
            .padding(
                start = 0.dp,
                end = 0.dp,
                bottom = 0.dp,
                top = 8.dp
            ),
        onClick =
        {
            Log.d("EDIT", "Edit Yours Workouts")
            onClick()
        },
        shape = CircleShape,
        border= BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
        colors = ButtonDefaults.buttonColors(
            //backgroundColor = Color.Transparent
            MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier
                //background(Color.Red)
                .fillMaxWidth(1f)
            ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .padding(
                        //start = 8.dp
                    )
                ,
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.background
            )
        }
    }
}

@Composable
fun ExerciseList(
    workoutItem: WorkoutDataItem,
    trainListForYou: TrainListViewModel,
) {
    val yourWorkouts = trainListForYou
        .workoutListGeneral
        .observeAsState(listOf())

    val yourExercises = trainListForYou
        .exerciseListGeneral
        .observeAsState(listOf())

    if ((yourExercises.value != null) && (yourExercises.value.size != 0)) {
        // упражнения для текущей тренировки
        workoutItem.exerciseList =
            trainListForYou.getExerciseListForOneWorkout(workoutItem.id)
        // Порядок выполнения
        Column(
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    //end = 16.dp,
                    //top = 16.dp,

                )
                .fillMaxWidth(1f)
                .fillMaxHeight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        )
        {
            var ind = 1
            // список упражнений
            for (ex in workoutItem.exerciseList)
             {
                if (workoutItem.exerciseList != null) {
                    ExerciseString(
                        (ind++).toString() + ". " + ex.title
                    )
                }
                }
            }
        }
}

@Composable
fun ExerciseString(exName: String)
{
    androidx.compose.material3.Text(
        text = exName,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        color = colorScheme.onBackground
    )
}

/*
@Preview
@Composable
fun WorkoutCardWhitePrev() {
    VotTakVotTheme(
        darkTheme = false)
    {
        val workoutItem = WorkoutDataItem()
        MyWorkoutCard(
            //workoutViewModel = workoutViewModel,
            workoutItem = workoutItem,
            onCardClickListener = {

            },
            onEditClickListener = {
                //trainList.changeLikedStatusList(it)
                //generalViewModel.changeLikedStatusListSearchResult(it)
            },
            onPlayClickListener = {

            }
        )
    }
}



@Preview
@Composable
fun WorkoutCardBlackPrev() {
    VotTakVotTheme(
        darkTheme = true)
    {
        //val generalViewModel =  GeneralViewModel()
        val source: List<WorkoutDataItem> = sourceListTrainsForYouExample
        // список тренировок
        //val trainList = TrainListViewModel()
        val workoutItem = WorkoutDataItem()
        MyWorkoutCard(
            //workoutViewModel = workoutViewModel,
            workoutItem = workoutItem,
            onCardClickListener = {

            },
            onEditClickListener = {
                //trainList.changeLikedStatusList(it)
                //generalViewModel.changeLikedStatusListSearchResult(it)
            },
            onPlayClickListener = {

            }
        )
    }
}
*/
