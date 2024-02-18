package com.example.vottakvot.navigation.screens

import android.annotation.SuppressLint
import android.app.LauncherActivity
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.database.ExerciseDataItem
import com.example.vottakvot.database.WorkoutDataItem
import com.example.vottakvot.navigation.navigationLogic.Screen
import com.example.vottakvot.ui.theme.MyExerciseCard
import com.example.vottakvot.ui.theme.MyWorkoutCard
import com.example.vottakvot.utils.WorkoutNameString
import com.google.accompanist.pager.ExperimentalPagerApi


@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun EditOneWorkoutScreen(
    navController: NavHostController,
    trainList: TrainListViewModel,
    dismissed: (exerciseDataItem: ExerciseDataItem) -> Unit
) {
    // выбранная тренировка
    var currentWorkoutId = trainList.currentWorkoutId
    //  список упражнений для выбранной тренировки
    var workoutItem = trainList.findWorkoutById(currentWorkoutId)
    if (workoutItem  == null)
        return
    val yourTrains = trainList
        .workoutListGeneral
        .observeAsState(listOf())
    val yourExercises = trainList
        .exerciseListGeneral
        .observeAsState(listOf())

    if ((yourExercises.value != null) && (yourExercises.value.size != 0)) {
        // упражнения для текущей тренировки
        workoutItem.exerciseList =
            trainList.getExerciseListForOneWorkout(workoutItem.id)



        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
                .background(MaterialTheme.colorScheme.background),

            ) {
            // строка заголовка
            EditWorkoutName(
                navController = navController,
                workoutItem = workoutItem,
                trainList = trainList
            )
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
            )
            {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f)
                )
                {
                    val exercises = yourExercises.value
                    LazyColumn(
                        //state = rememberLazyListState(),
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .fillMaxWidth()
                            .fillMaxHeight(1f),
                    ) {
                        // список Моих тренировок
                        items(exercises, key = { it.id } )
                        {
                            if (it.workoutId == workoutItem.id) {
                                //var repetitions = remember { mutableStateOf(it.repetitions) }
                                //var exercise = remember { mutableStateOf(it) }
                                //  items(trains, key = { it.id })
                                // {
                                // при удалении тренировки
                                val dismissState = rememberDismissState()
                                if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                                    dismissed(it)
                                    // удаление моего упражнения
                                    //trainList.removeExercise(it.id)
                                    val context = LocalContext.current
                                    Toast.makeText(
                                        context,
                                        "Упражнение " + it.title + " было удалено",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                SwipeToDismiss(
                                    state = dismissState,
                                    directions = setOf(DismissDirection.EndToStart),
                                    background = {
                                        // карточка упражнения
                                        Card(
                                            modifier = Modifier
                                                .background(MaterialTheme.colorScheme.background)
                                                .fillMaxSize()
                                                .clickable {
                                                    //onCardClickListener(workoutItem)
                                                }
                                                .fillMaxWidth()
                                                .padding(
                                                    start = 8.dp,
                                                    end = 8.dp,
                                                    top = 8.dp
                                                ),
                                            //.height(140.dp),
                                            backgroundColor = Color(0xFFDC0720),
                                            shape = RoundedCornerShape(
                                                topStart = 16.dp,
                                                topEnd = 16.dp,
                                                bottomStart = 16.dp,
                                                bottomEnd = 16.dp
                                            ),
                                            border = BorderStroke(
                                                1.dp,
                                                MaterialTheme.colorScheme.onBackground
                                            ))
                                        {
                                            Row(
                                                modifier = Modifier,
                                                horizontalArrangement = Arrangement.End,
                                                verticalAlignment = Alignment.CenterVertically
                                            )
                                            {
                                                // значок удаления
                                                Icon(
                                                    modifier = Modifier
                                                        .size(60.dp)
                                                        .padding(
                                                            end = 16.dp
                                                            //start = 8.dp
                                                        ),
                                                    imageVector = Icons.Default.DeleteOutline,
                                                    contentDescription = null,
                                                    tint = Color.White
                                                )
                                            }
                                        }
                                    },
                                    dismissContent = {
                                        MyExerciseCard(
                                            navController = navController,
                                            //repetitions = repetitions, // список подходов с повторениями
                                            trainList = trainList,
                                            //workoutItem = workoutItem,
                                            exerciseItem = it
                                        )
                                        /*{
                                            var a = it

                                        }*/
                                    }
                                )
                            }
                        }

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
                // кнопка добавления упражнения из списка
                CreateButton(
                    modifier = Modifier
                        .padding(
                            //end = 8.dp,
                            bottom = 8.dp
                        )
                        .height(60.dp)
                        .fillMaxWidth(0.9f),
                    text = "Добавить упражнение",
                )
                {
                    // создание новой тренировки
                    var myNewWorkout = WorkoutDataItem(
                        title = "Моя тренировка",
                        isAddedToMyTrainList = true
                    )
                    // переход на странцу выбора из списка тренировок
                    navController.navigate(Screen.ExerciseList.route)
                    // добавление в БД
                    //trainList.insertOneWorkoutWithExercise(myNewWorkout)


                }
            }
        }
    }

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun EditWorkoutName(
    navController: NavHostController,
    workoutItem: WorkoutDataItem,
    trainList: TrainListViewModel,
)
{
    val isWorkoutNameEditNow = mutableStateOf(false)
    val editingStep = mutableStateOf(-1)
    var focusRequester by remember { mutableStateOf(FocusRequester()) }
    Row(
        modifier = Modifier
            //.background(Color.Green)
            .fillMaxWidth()
            .height(80.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Row(
            modifier = Modifier
                //.background(Color.Gray)
                .weight(0.2f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        )
        {
            // выбранная тренировка
            var currentWorkoutId = trainList.currentWorkoutId
            //  список упражнений для выбранной тренировки
            var workoutItem = trainList.findWorkoutById(currentWorkoutId)
            IconCloseButton(
                modifier = Modifier
                    .size(
                        45.dp
                    )
                    .padding(
                        top = 8.dp,
                        //start = 8.dp
                    ),
                horizontalArrangement = Arrangement.Start,
                iconResId = Icons.Outlined.ArrowBackIos,
                iconResIdPressed = Icons.Outlined.ArrowBackIos,
                isChanged = true
            )
            {
                navController.popBackStack()
            }
        }
        Row(
            modifier = Modifier
                //.background(Color.Yellow)
                .weight(0.9f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
            )
        {
            WorkoutNameString(
                workoutItem = workoutItem,
                trainList = trainList,
                step = -2,
                editingStep = editingStep,
                focusRequester = focusRequester,
                isWorkoutNameEditNow = isWorkoutNameEditNow,
                stringHeight = 100.dp,
                fontSize = 30.sp,
            )
            {

            }
            /*
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(
                        // start = 8.dp
                    ),
                text = workoutItem.title,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                //fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
            )
            */
            }
            Row(
                modifier = Modifier
                    //.background(Color.Gray)
                    .weight(0.2f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
                )
        {
            //  кнопка редактирования названия тренировки
            EditWorkoutNameButton()
            {
                // переходим в режим редактирования названия тренировки
                isWorkoutNameEditNow.value = true
                editingStep.value = -2
            }
        }
    }

}

// редактирование тренировок
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun EditWorkoutNameButton(
    //navController: NavHostController,
    //modifier: Modifier,
    //trainList: TrainListViewModel,
    //currentWorkout: WorkoutDataItem,
    //inquirerViewModel: InquirerViewModel,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            //.background(Color.Red)
            .fillMaxWidth(1f)
        ,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Box(
        )
        {
            OutlinedButton(
                modifier = Modifier
                    //.background(Color.Gray)
                    .width(55.dp)
                    .height(55.dp)
                    .padding(
                        start = 0.dp,
                        end = 0.dp,
                        bottom = 0.dp,
                        top = 0.dp
                    ),
                onClick =
                {
                    Log.d("EDIT", "Edit Yours Workouts")
                    onClick()
                },
                shape = CircleShape,
                //border = BorderStroke(2.dp, MaterialTheme.colorScheme.onError),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = //Color.Transparent
                    MaterialTheme.colorScheme.primary
                )
            ) {

            }
            Row(
                modifier = Modifier
                    .height(55.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Icon(
                    modifier = Modifier
                        .size(45.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()

                        .padding(
                            start = 8.dp,
                            //top = 8.dp
                        ),
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background
                )
            }
        }
    }
}


/*
@Preview
@Composable
fun WorkoutCardWhitePrev() {
    VotTakVotTheme(
        darkTheme = false)
    {
        EditOneWorkoutScreen(
            //exerciseItem = ExerciseDataItem()
        )
    }
}



@Preview
@Composable
fun WorkoutCardBlackPrev() {
    VotTakVotTheme(
        darkTheme = true)
    {
        EditOneWorkoutScreen(
            //exerciseItem = ExerciseDataItem()
        )
    }
}
*/

