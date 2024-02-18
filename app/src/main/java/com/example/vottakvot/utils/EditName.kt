package com.example.vottakvot.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.database.ExerciseDataItem
import com.example.vottakvot.database.WorkoutDataItem


// ДИСТАНЦИЯ ПРИСТРЕЛКИ
@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WorkoutNameString(
    workoutItem: WorkoutDataItem,
    trainList: TrainListViewModel,
    step: Int,
    editingStep: MutableState<Int>,
    focusRequester: FocusRequester,
    isWorkoutNameEditNow: MutableState<Boolean>,
    //users: UserListViewModel,
    stringHeight: Dp = 80.dp,
    fontSize: TextUnit = 30.sp,
    colorText: Color = MaterialTheme.colorScheme.primary,
    onParametrClick: () -> Unit
) {
    val yourExercises = trainList
        .exerciseListGeneral
        .observeAsState(listOf())


   // var exercise = ExerciseDataItem()
    //var step = 0
    // текущее упражнение
    //val currentExercise = trainList.findExerciseById(exerciseItem)

    // текущее имя тренировки
    var value = workoutItem.title
        //.repetitions.repetitions[step]
        //yourExercises.value
        //exercise.repetitions.repetitions[step]
    val windowInfo = LocalWindowInfo.current
    // первоначальное значение
    var stateText by remember {
        mutableStateOf(TextFieldValue(value.toString()))
    }
    val keyboard = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier
            //.background(Color.Gray)
            .clickable {
                onParametrClick()
            }
            .height(stringHeight)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    )
    {
        Row(
            modifier = Modifier
                //.fillMaxWidth(0.5f)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        )
        {
            if (editingStep.value == step) {// && (isRepetitionEditNow.value)) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(1f)
                        .padding(
                        )
                    ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                )
                {
                    BasicTextField(
                        value = stateText,
                        onValueChange = {
                            val v = it.text
                            if (v!=null)  {
                                stateText = it
                                workoutItem.title = v

                                // обновить текущую тренировку
                                trainList.updateWorkout(workoutItem)
                                //currentUser.shootingDistance = v
                            }
                        },
                        modifier = Modifier
                            .padding(
                                end = 8.dp
                            )
                            .focusRequester(
                                focusRequester
                            )
                            .onFocusChanged { focusState ->
                                if (focusState.isFocused) {
                                    keyboard?.show()
                                    val text = stateText.text
                                    stateText = stateText.copy(
                                        selection = TextRange(0, text.length)
                                    )
                                }
                                else{
                                    if (isWorkoutNameEditNow.value) {
                                        focusRequester.requestFocus()
                                    } else {
                                    }
                                }
                            },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Text

                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                workoutItem.title = stateText.text
                                // обновить значение в базе
                                trainList.updateWorkout(workoutItem)

                                /*currentUser.shootingDistance = stateText.text.toInt()
                                users.updateUser(currentUser)*/
                                isWorkoutNameEditNow.value = false
                                editingStep.value = -1
                            }
                        ),
                        singleLine = true,
                        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                        textStyle = TextStyle(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = fontSize,
                            textAlign = TextAlign.Center,
                        ),
                    )

                    /*LaunchedEffect(focusRequester) {
                        delay(100)
                        focusRequester.requestFocus()
                    }*/
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(
                            end = 8.dp,
                        )
                    ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                )
                {
                    workoutItem.title = stateText.text
                    // обновить значение в базе
                    trainList.updateWorkout(workoutItem)

                    /*currentUser.shootingDistance = stateText.text.toInt()
                    users.updateUser(currentUser)*/
                    Text(
                        modifier = Modifier
                            .clickable {
                                /*isRiflingDirectionNow.value = false
                                isRiflingPitchNow.value = false
                                isSightHeightNow.value = false*/
                                isWorkoutNameEditNow.value = true
                            },
                        //maxLines = 1,
                        lineHeight = 40.sp,
                        text = workoutItem.title.toString(),
                        //currentUser.shootingDistance.toString()  + " " + units,
                        //fontWeight = FontWeight.Bold,
                        fontSize = fontSize,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

        }
    }
}

