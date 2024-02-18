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
fun RepetitionString(
    step: Int,
    repetitionValue: Int, // текущее значение повтора
    editingStep: MutableState<Int>,
    focusRequester: FocusRequester,
    isRepetitionEditNow: MutableState<Boolean>,
    stringHeight: Dp = 30.dp,
    fontSize: TextUnit = 15.sp,
    colorText: Color = MaterialTheme.colorScheme.primary,
    minValue: Int = 10,
    maxValue: Int = 50,
    onParametrClick: () -> Unit,
    onValueChange: (Int) -> Unit // при изменении значения параметра
) {
    // текущее значение повтора
    var value = repetitionValue
    // первоначальное значение
    var stateText by remember {
        mutableStateOf(TextFieldValue(value.toString()))
    }
    val keyboard = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier
            .clickable {
                onParametrClick()
            }
            .height(stringHeight)
            .width(30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    )
    {
        Row(
            modifier = Modifier
                //.fillMaxWidth(0.5f)
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
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
                    horizontalArrangement = Arrangement.End
                )
                {
                    BasicTextField(
                        value = stateText,
                        onValueChange = {
                            val v = it.text.toIntOrNull()
                            if ((v!=null) && (v.toInt() <= maxValue)) {
                                stateText = it
                                // изменить значение повторений
                                onValueChange(v)
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
                                    if (isRepetitionEditNow.value) {
                                        focusRequester.requestFocus()
                                    } else {
                                    }
                                }
                            },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Decimal

                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                if (stateText.text.toInt() > maxValue)
                                {
                                    stateText = TextFieldValue(maxValue.toString())
                                }
                                else if (stateText.text.toInt() < minValue)
                                {
                                    stateText = TextFieldValue(minValue.toString())
                                }
                                // изменить значение повторений
                                onValueChange(stateText.text.toInt())
                                isRepetitionEditNow.value = false
                                editingStep.value = -1
                            }
                        ),
                        singleLine = true,
                        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                        textStyle = TextStyle(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = fontSize,
                            textAlign = TextAlign.End,
                        ),
                    )
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
                    horizontalArrangement = Arrangement.End
                )
                {
                    if (stateText.text.toInt() > maxValue)
                    {
                        stateText = TextFieldValue(maxValue.toString())
                    }
                    else if (stateText.text.toInt() < minValue)
                    {
                        stateText = TextFieldValue(minValue.toString())
                    }
                    // изменить значение повторений
                    onValueChange(stateText.text.toInt())
                    Text(
                        modifier = Modifier
                            .clickable {
                                isRepetitionEditNow.value = true
                            },
                        text = stateText.text,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

