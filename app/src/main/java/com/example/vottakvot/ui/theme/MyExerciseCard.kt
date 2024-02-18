package com.example.vottakvot.ui.theme

import android.annotation.SuppressLint
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Add
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
import androidx.compose.ui.layout.ContentScale
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
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.InquirerViewModel
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.database.BodyType
import com.example.vottakvot.database.ExerciseDataItem
import com.example.vottakvot.database.RepetitionList
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

@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun MyExerciseCard(
    navController: NavHostController,
    exerciseItem: ExerciseDataItem,
    trainList: TrainListViewModel,
) {
    // карточка тренировки
    Card(
        modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    try {

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
                            //.background(Color.Red)
                            .background(colorScheme.background)
                            .fillMaxWidth(0.5f)
                    )
                    {
                        Gif(
                            gifUrl = exerciseItem.url,
                            modifier = Modifier
                                .width(150.dp)
                                .height(100.dp)

                        )
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
                                    // переходим на страницу выбранного упражнения
                                    trainList.currentExerciseId = exerciseItem.id
                                    navController.navigate(Screen.Exercise.route)
                                    // к предыдущей странице приветствия
                                }

                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                //var repetitionsChanged = remember { mutableStateOf(false) }
                // список подходов
                Column()
                {

                    var repetitions = remember { mutableStateOf(exerciseItem.repetitions) }
                    // упражнение с подходами и повторениями
                    //if (repetitionsChanged.value) {

                        MyExerciseList(
                            navController = navController,
                            repetitions = repetitions,// список повторений
                            //trainList = trainList,
                            // при удалении подхода
                            onDeleteClickListener =
                            {
                                // число повторения для текущего упражнения
                                var currentRepetitions = RepetitionList()
                                currentRepetitions.repetitions.clear()
                                for (i in 0..exerciseItem.repetitions.repetitions.size - 1) {
                                    // копировать все повторения, кроме удаленного
                                    if (i != it) {
                                        currentRepetitions.repetitions.add(exerciseItem.repetitions.repetitions[i])
                                    }
                                }
                                // обновить повторения для текущего упражнения
                                exerciseItem.repetitions = currentRepetitions
                                repetitions.value = currentRepetitions
                                trainList.updateExercise(exerciseItem)
                            },
                            //  при добавлении подхода
                            onAddClickListener = {
                                //trainList.updateExercise(it)
                                // число повторения для текущего упражнения
                                var currentRepetitions = RepetitionList()
                                currentRepetitions.repetitions.clear()
                                currentRepetitions.repetitions.add(10 + it)
                                if (exerciseItem != null) {
                                    //currentRepetitions = exerciseItem.repetitions
                                    for (i in 0..exerciseItem.repetitions.repetitions.size-1) {
                                        currentRepetitions.repetitions.add(exerciseItem.repetitions.repetitions[i])
                                    }
                                }

                                //currentRepetitions.repetitions.add(10 + it)
                                // обновить повторения для текущего упражнения
                                exerciseItem.repetitions = currentRepetitions
                                repetitions.value = currentRepetitions
                                trainList.updateExercise(exerciseItem)
                            }
                        )
                    }
                    }
                }


                }


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun Gif(
    gifUrl: String,
    modifier: Modifier = Modifier
)
{
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
            ),
        backgroundColor = Color.White,
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
            bottomStart = 16.dp,
            bottomEnd = 16.dp
        ),
        border = BorderStroke(1.dp, colorScheme.onSurfaceVariant)

    )
    {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(gifUrl)
                .decoderFactory(ImageDecoderDecoder.Factory())
                .build(),
            contentDescription = null,
            modifier = modifier
                .fillMaxHeight()
                .clip(RoundedCornerShape(6.dp))
            .aspectRatio(1280/880f)
            ,
            contentScale = ContentScale.FillHeight,
            )
    }
}


// номер тренировки
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun NumberExerciseStepButton(
     ind: Int,
     isAddButton: Boolean = false, // кнопка добавления нового подхода
     onClick: () -> Unit
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
            if (isAddButton)
                onClick()
        },
        shape = CircleShape,
        border= BorderStroke(2.dp, MaterialTheme.colorScheme.surfaceVariant),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isAddButton)
               MaterialTheme.colorScheme.primary
            else Color.Transparent
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
            ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            if (!isAddButton) {
                Text(
                    text = ind.toString(),
                    fontSize = 20.sp,
                    color = colorScheme.onBackground,
                )
            }
            else{
                Icon(
                    modifier = Modifier
                        //.background(Color.Red)
                        .size(35.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()

                        .padding(
                            //start = 8.dp,
                            //top = 8.dp
                        ),
                    imageVector = Icons.Outlined.Add,
                    contentDescription = null,
                    tint = Color.White
                )
            }
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
fun RemoveExerciseStepButton(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
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
@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun MyExerciseList(
    navController: NavHostController,
    repetitions: MutableState<RepetitionList>, // список подходов с повторениями
    onDeleteClickListener: (Int) -> Unit,
    onAddClickListener: (Int) -> Unit,
) {
    // шаг, который в данный момент редактируется
    val editingStep = mutableStateOf(-1)
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
        var repetitionsChanged = remember { mutableStateOf(false) }
        if (repetitionsChanged.value) {
            repetitionsChanged.value = false
        }
        for (ind in 0..repetitions.value.repetitions.size-1)
            {
                ExerciseStepRow(
                    //navController = navController,
                    repetitionValue = repetitions.value.repetitions[ind],
                    editingStep = editingStep,
                    step = ind,
                    // при удалении строчки
                    onDeleteClickListener = {
                        onDeleteClickListener(it)
                                            },
                    // при редактировнии параметра
                    onParametrValueChange = {
                        repetitions.value.repetitions[ind] = it
                    }

                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            // кнопка добавления нового подхода
            AddStepRow(
                step = repetitions.value.repetitions.size,
            )
            // кнопка добавления нового подхода
            {
                onAddClickListener(repetitions.value.repetitions.size,)
            }
    }
}


// Добавить новые повторения
@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun AddStepRow(
    step: Int,
    onAddStep: () -> Unit,
) {
    //  редактируемое количество повторов
    Row(
        modifier = Modifier
            .fillMaxWidth(),

        )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.7f),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            NumberExerciseStepButton(
                ind = step,
                isAddButton = true
            ) {
                onAddStep()
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun ExerciseStepRow(
    repetitionValue: Int, // кол-во повторений на данном подходе
    editingStep: MutableState<Int>,
    step: Int,
    onDeleteClickListener: (Int) -> Unit,
    onParametrValueChange: (Int) -> Unit, // при изменении значени] параметра
)
{
    //  редактируемое количество повторов
    val isRepetitionsEditNow = mutableStateOf(false)
    var focusRequester by remember { mutableStateOf(FocusRequester()) }
    Row (
        modifier = Modifier
            .fillMaxWidth(),

    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.7f),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            NumberExerciseStepButton(step) {}
            Spacer(modifier = Modifier.width(5.dp))
            androidx.compose.material3.Text(
                text = "подход:",
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = colorScheme.onBackground
            )
            Spacer(modifier = Modifier.width(8.dp))
            RepetitionString(
                step = step,
                repetitionValue = repetitionValue,
                editingStep = editingStep,
                focusRequester = focusRequester,
                isRepetitionEditNow = isRepetitionsEditNow,
                onParametrClick = {},
                onValueChange = {
                    // при изменении значения параметра
                    onParametrValueChange(it)
                }
            )
            androidx.compose.material3.Text(
                text = "секунд",
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = colorScheme.onBackground
            )
        }
        Row(
            modifier = Modifier

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