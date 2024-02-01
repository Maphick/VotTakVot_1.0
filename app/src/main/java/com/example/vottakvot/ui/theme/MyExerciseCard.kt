package com.example.vottakvot.ui.theme

import android.annotation.SuppressLint
import android.os.Build.VERSION.SDK_INT
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.PlaylistAdd
import androidx.compose.material.icons.sharp.PlayCircleFilled
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.InquirerViewModel
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.database.BodyType
import com.example.vottakvot.database.ExerciseDataItem
import com.example.vottakvot.database.WorkoutDataItem
import com.example.vottakvot.navigation.navigationLogic.Screen
import com.example.vottakvot.navigation.screens.FindYoursWorkouts
import com.example.vottakvot.navigation.screens.LazyColumnExerciseItem
import com.example.vottakvot.navigation.screens.MoreTrainButton
import com.example.vottakvot.navigation.screens.SearchWorkoutsButton
import com.example.vottakvot.utils.RepetitionString
import com.example.vottakvot.utils.TextBlock
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun MyExerciseCard(
    navController: NavHostController,
    exerciseItem: ExerciseDataItem,
    workoutItem: WorkoutDataItem,
    trainList: TrainListViewModel,
    onDeleteClickListener: (Int) -> Unit
    /*onCardClickListener: (WorkoutDataItem) -> Unit,
    onPlayClickListener: (WorkoutDataItem) -> Unit,
    onEditClickListener: (WorkoutDataItem) -> Unit,
    */
) {
    val yourTrains = trainList
        .workoutListGeneral
        .observeAsState(listOf())

    val yourExercises = trainList
        .exerciseListGeneral
        .observeAsState(listOf())

    val focusManager = LocalFocusManager.current
    // шаг, который в данный момент редактируется
    val editingStep = mutableStateOf(-1)
    // карточка тренировки
    Card(
        modifier = Modifier
            .background(Color.White)
                .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    try {
                       // focusManager.clearFocus()
                    }
                    catch (e:Exception)
                    {
                        Log.d("Exeption", "focusManager.clearFocus()")
                    }
                })
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

                    Row(
                        modifier = Modifier
                            .background(colorScheme.background)
                            .fillMaxWidth(0.5f)
                    )
                    {
                        Gif(
                            //modifier = modifier
                        )
                        /*Image(
                            painter =  painterResource(id = exerciseItem.img),
                            contentDescription = null
                        )*/
                    }
                    Column(
                        modifier = Modifier
                            .padding(
                                start = 8.dp
                            )
                    )
                    {
                        Row(
                            horizontalArrangement = Arrangement.Center
                        )
                        {
                            Text(
                                text = "${exerciseItem.title}",
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp,
                                color = colorScheme.primary,
                            )
                        }
                        MoreTrainButton(
                            modifier = Modifier
                                .fillMaxWidth(1f),
                            text = stringResource(R.string.more_details),
                        ) {
                            GlobalScope.launch {
                                withContext(Dispatchers.Main) {
                                    // переходим на страницу поиска тренировок для Вас
                                   // navController.navigate(Screen.SearchResultForYou.route)
                                    // к предыдущей странице приветствия
                                }

                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                // список подходов
                Column {

                }
                (
                    MyExerciseList(
                        navController = navController,
                        exerciseItem = exerciseItem,
                        workoutItem = workoutItem,
                        trainList = trainList,
                        editingStep = editingStep
                    )
                    {
                        onDeleteClickListener(it)

                    }
                        )
                }
            }
        }


@Composable
fun Gif(
    modifier: Modifier = Modifier
)
{
    Card(
        modifier = Modifier
            .clickable {
                //onCardClickListener(workoutItem)
            }
            .fillMaxWidth()
            .padding(
                start = 2.dp,
                end = 2.dp,
                top = 2.dp,
                bottom = 2.dp
            ),
        //.height(140.dp),
        backgroundColor = colorScheme.surface,
        shape = RoundedCornerShape(
            topStart = 8.dp,
            topEnd = 16.dp,
            bottomStart = 16.dp,
            bottomEnd = 16.dp
        ),
        border = BorderStroke(1.dp, colorScheme.onBackground)
    )
    {
        val imageLoader = ImageLoader.Builder(LocalContext.current)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()

        Image(
            painter = rememberAsyncImagePainter(R.drawable.vpxr, imageLoader),
            contentDescription = null,
            modifier = modifier
                //.fillMaxWidth()
                .size(
                    width = 200.dp,
                    height = 100.dp
                )
        )
    }
}

// редактирование тренировок
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun EditExerciseButton(
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

// номер тренировки
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun NumberExerciseStepButton(
     ind: Int
) {
    OutlinedButton(
        modifier = Modifier
            .width(45.dp)
            .height(45.dp)
            .padding(
                start = 0.dp,
                end = 0.dp,
                bottom = 0.dp,
                top = 0.dp
            ),
        onClick =
        {
            //Log.d("EDIT", "Edit Yours Workouts")
            //onClick()
        },
        shape = CircleShape,
        border= BorderStroke(2.dp, MaterialTheme.colorScheme.surfaceVariant),
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
            Text(
                text = ind.toString(),
                fontSize = 20.sp,
                color = colorScheme.onBackground,
            )
        }
    }
}


// редактирование тренировок
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun EditExerciseStepButton(
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
                    .width(45.dp)
                    .height(45.dp)
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
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.onError),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                    //MaterialTheme.colorScheme.primary
                )
            ) {

            }
            Row(
                modifier = Modifier
                    .height(35.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Icon(
                    modifier = Modifier
                        .size(35.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()

                        .padding(
                            start = 8.dp,
                            top = 8.dp
                        ),
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}



@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun RemoveExerciseButton(
    //navController: NavHostController,
    //modifier: Modifier,
    //trainList: TrainListViewModel,
    //currentWorkout: WorkoutDataItem,
    //inquirerViewModel: InquirerViewModel,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = Modifier
            .width(50.dp)
            .height(50.dp)
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
        border= BorderStroke(2.dp, MaterialTheme.colorScheme.onSurfaceVariant),
        colors = ButtonDefaults.buttonColors(
            //backgroundColor = Color.Transparent
            MaterialTheme.colorScheme.onError
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

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun RemoveExerciseStepButton(
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
            .fillMaxWidth(0.5f)
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
                    .width(45.dp)
                    .height(45.dp)
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
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.onError),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                //MaterialTheme.colorScheme.primary
                )
            ) {

            }
            Row(
                modifier = Modifier
                    .height(35.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Icon(
                    modifier = Modifier
                        .size(35.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()

                        .padding(
                            start = 8.dp,
                            top = 8.dp
                        ),
                    imageVector = Icons.Default.DeleteOutline,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
    }
    }
}



// список подходов
@SuppressLint("UnrememberedMutableState")
@Composable
fun MyExerciseList(
    navController: NavHostController,
    exerciseItem: ExerciseDataItem,
    workoutItem: WorkoutDataItem,
    trainList: TrainListViewModel,
    editingStep: MutableState<Int>,
    onDeleteClickListener: (Int) -> Unit
) {
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
        //var ind = 1
        // список упражнений
        //for (steps in exerciseItem.repetitions.repetitions)


        for (ind in 0..exerciseItem.repetitions.repetitions.size - 1) {
            ExerciseStepRow(
                navController = navController,
                workoutItem = workoutItem,
                exerciseItem = exerciseItem,
                trainList = trainList,
                editingStep = editingStep,
                step = ind

            )
            {
                onDeleteClickListener(ind)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun ExerciseStepRow(
    navController: NavHostController,
    exerciseItem: ExerciseDataItem,
    workoutItem: WorkoutDataItem,
    trainList: TrainListViewModel,
    editingStep: MutableState<Int>,
    step: Int,
    onDeleteClickListener: (Int) -> Unit,
)
{
    //  редактируемое количество повторов
    val isRepetitionsEditNow = mutableStateOf(false)
    var focusRequester by remember { mutableStateOf(FocusRequester()) }
    Row (
        modifier = Modifier
            //.background(Color.Gray)
            .fillMaxWidth(),

    )
    {
        Row(
            modifier = Modifier
                //.background(Color.Gray)
                .fillMaxWidth(0.7f),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            NumberExerciseStepButton(step)
            Spacer(modifier = Modifier.width(5.dp))
            androidx.compose.material3.Text(
                text = "подход:",
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = colorScheme.onBackground
            )
            Spacer(modifier = Modifier.width(8.dp))


            RepetitionString(
                navController = navController,
                exerciseItem = exerciseItem,
                workoutItem = workoutItem,
                trainList = trainList,
                step = step,
                editingStep = editingStep,
                focusRequester = focusRequester,
                isRepetitionEditNow = isRepetitionsEditNow,
            ) {

            }
            androidx.compose.material3.Text(
                text = "секунд",
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = colorScheme.onBackground
            )
        }
        Row(
            modifier = Modifier
                //.background(Color.Green)
            ,
            horizontalArrangement = Arrangement.End
        )
        {
            RemoveExerciseStepButton()
            {
                onDeleteClickListener(step)
            }
            EditExerciseStepButton()
            {
                editingStep.value = step
                isRepetitionsEditNow.value = true
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
        MyExerciseCard(
            exerciseItem = ExerciseDataItem()
        )
    }
}



@Preview
@Composable
fun WorkoutCardBlackPrev() {
    VotTakVotTheme(
        darkTheme = true)
    {
        MyExerciseCard(
            exerciseItem = ExerciseDataItem()
        )
    }
}

*/