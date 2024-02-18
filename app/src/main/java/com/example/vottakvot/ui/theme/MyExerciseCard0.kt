package com.example.vottakvot.ui.theme

import android.annotation.SuppressLint
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
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
fun MyExerciseCard0(
    //navController: NavHostController,
    //exerciseItem: ExerciseDataItem,
    //trainList: TrainListViewModel,
) {
    val exerciseItem = ExerciseDataItem()
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
                    val gifUrl = "https://v2.exercisedb.io/image/lJIB6CnsyUuPI8"
                    Gif(
                        gifUrl = gifUrl,
                        modifier = Modifier
                            .fillMaxHeight(1f)
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
                                // переходим на страницу поиска тренировок для Вас
                                // navController.navigate(Screen.SearchResultForYou.route)
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
/*
                MyExerciseList(
                    navController = navController,
                    //repetitionsChanged = repetitionsChanged,
                    repetitions = repetitions,// список повторений
                    //exerciseItem = exerciseItem,
                    //workoutItem = workoutItem,
                    trainList = trainList,
                    //editingStep = editingStep,
                    // при удалении подхода
                    onDeleteClickListener =
                    {
                        //trainList.updateExercise(trainList.findExerciseById(it))



                        //onDeleteClickListener(it)
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
                        //repetitions.value = currentRepetitions
                        //trainList.removeExercise(exerciseItem.id)
                        trainList.updateExercise(exerciseItem)
                        //trainList.updateWorkout(workoutItem)

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
                        //repetitions.value = currentRepetitions
                        //trainList.removeExercise(exerciseItem.id)
                        //trainList.removeExercise(exerciseItem.id)
                        //trainList.insertExercise(exerciseItem)
                        trainList.updateExercise(exerciseItem)
                        //trainList.updateWorkout(workoutItem)
                    }
                )*/
            }
        }
    }


}





@RequiresApi(Build.VERSION_CODES.P)
@Preview
@Composable
fun WorkoutCardWhitePrev() {
    VotTakVotTheme(
        darkTheme = false)
    {
        MyExerciseCard0(
            //exerciseItem = ExerciseDataItem()
        )
    }
}



@RequiresApi(Build.VERSION_CODES.P)
@Preview
@Composable
fun WorkoutCardBlackPrev() {
    VotTakVotTheme(
        darkTheme = true)
    {
        MyExerciseCard0(
            //exerciseItem = ExerciseDataItem()
        )
    }
}
