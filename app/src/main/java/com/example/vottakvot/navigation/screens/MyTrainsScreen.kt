package com.example.vottakvot.navigation.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.PlaylistAdd
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.database.WorkoutDataItem
import com.example.vottakvot.navigation.navigationLogic.Screen
import com.example.vottakvot.ui.theme.InfoIconWithText
import com.example.vottakvot.ui.theme.MyWorkoutCard
import com.example.vottakvot.ui.theme.VotTakVotTheme
import com.example.vottakvot.ui.theme.WorkoutCard
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun MyTrainsScreen(
    navController: NavHostController,
    trainList: TrainListViewModel
) {

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
                        text = stringResource(R.string.workouts_my),
                        isIconVisible = true,
                        //iconResId =  iconResId
                    )
                    val yourTrains = trainList
                        .workoutListGeneral
                        .observeAsState(listOf())
                    val yourExercises = trainList
                        .exerciseListGeneral
                        .observeAsState(listOf())

                    if ((yourTrains.value != null ) && (yourTrains.value.size !=0 ))
                    {
                    val trains =   yourTrains.value
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(1f)
                            .background(MaterialTheme.colorScheme.background),
                    ) {
                        // список Моих тренировок
                        items(trains)
                        {
                            // если помечена как "Моя тренировка"
                           if (it.isAddedToMyTrainList) {
                                MyWorkoutCard(
                                    navController = navController,
                                    trainListForYou = trainList,
                                    workoutItem = it,
                                    //trainListForYou = trainList,
                                    onCardClickListener = {
                                    },
                                    onEditClickListener = {
                                        trainList.changeLikedStatusList(it)
                                    },
                                    onPlayClickListener = {
                                    }
                                )
                           }
                        }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        //.background(Color.Red)
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Bottom
                )
                {
                    Row(
                        modifier = Modifier,
                            //.background(Color.Gray),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.Bottom
                    )
                    {
                        CreateButton(
                            modifier =Modifier
                                .padding(
                                    //end = 8.dp,
                                    bottom = 8.dp
                                )
                                .height(80.dp)
                                .fillMaxWidth(0.55f),
                            text = "Создать",
                        )
                        {
                            // создание новой тренировки
                            var myNewWorkout = WorkoutDataItem(
                                title = "Моя тренировка",
                                isAddedToMyTrainList = true
                            )
                            // добавление в БД
                            trainList.insertOneWorkoutWithExercise(myNewWorkout)


                        }
                    }
                }
            }
        }
    }


@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun CreateButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            //.fillMaxWidth(1f)

            .padding(
                //start = 8.dp,
                end = 8.dp,
                //bottom = 16.dp
            ),
        onClick =
        {
            onClick()
        },
        shape = RoundedCornerShape(20),
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
                iconResId = Icons.Filled.Add,
                iconSize = 30.dp,
                text = text,
                fontSize = 20.sp,
                isBold = true
            )
        }
    }
}



@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Preview
@Composable
fun WdforkoutCardBlackPrev() {
    VotTakVotTheme(
        darkTheme = true)
    {
        Column(
            modifier = Modifier
                //.background(Color.Red)
                .fillMaxWidth()
                .fillMaxHeight(0.9f),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
        )
        {
            Row(
                modifier = Modifier,
                //.background(Color.Gray),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            )
            {
                CreateButton(
                    modifier =Modifier
                        .padding(
                            //end = 8.dp,
                            bottom = 8.dp
                        )
                        .fillMaxWidth(0.4f),
                    text = "Создать",
                )
                {

                }
            }
        }
    }
}

