package com.example.vottakvot.navigation.screens

import android.widget.CheckBox
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.PlaylistAdd
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.InquirerViewModel
import com.example.vottakvot.data.DataStoreRepository
import com.example.vottakvot.domain.WorkoutDataItem
import com.example.vottakvot.isOnboardingPassedApp
import com.example.vottakvot.ui.theme.VotTakVotTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.example.vottakvot.domain.InquirerPage
import com.example.vottakvot.navigation.navigationLogic.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun InquirerDataGeneration(inquirerViewModel: InquirerViewModel)
{
    inquirerViewModel.createExampleInquirerPageList()
}

// экраны опросника
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun InquirerScreen(
    //context: Context,
    navController: NavHostController,
    inquirerViewModel: InquirerViewModel
) {
    // заполнение моками
    InquirerDataGeneration(inquirerViewModel)
    //  все страницы опросника
    val pages = inquirerViewModel.getInquirerPagesList()
    val pagerCount = pages.size
    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(
                    top = 24.dp,
                    end = 16.dp
                ),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        )
        {
            
            IconCloseButton(
                modifier = Modifier
                    .size(
                        35.dp
                    ),
                iconResId = Icons.Outlined.PlaylistAdd,
                iconResIdPressed = Icons.Outlined.Close,
                isChanged = true
            )
            {
                // если закрываем - идём на домашний экран БЕЗ ОНБОРДИНГА
                navController.navigate(Screen.Home.route)
                // закрыть окно с поиском
                //navController.popBackStack()
                //onAddedClickListener(workoutItem)
            }
        }
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            //.weight(10f),
            count = pagerCount,
            state = pagerState,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            position ->
            InquirerPagerScreen(

                inquirerPage = pages[position],
                state = false,
                onStateChange = {

                }
            )
        }

        Spacer(
            modifier = Modifier
                .height(10.dp)
        )

        InquirerBottomNav(
            inquirerViewModel,
            navController = navController,
            pagerState = pagerState,
            pagerCount = pagerCount
        )
    }
}


@Composable
fun InquirerPagerScreen(
    inquirerPage: InquirerPage,
    state: Boolean,
    //answersList: OnBoardingAnswersListViewModel,
    onStateChange: (InquirerPage) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(0.5f)
            .padding(
                start = 16.dp,
                end = 16.dp
            ),
        
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(
            modifier = Modifier
            //  .width(20.dp)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 0.dp
                ),
            color = colorScheme.onBackground,
            text = inquirerPage.question,
            fontSize = 24.sp,
            lineHeight = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,

            )
        Spacer(
            modifier = Modifier
                .height(100.dp)
        )
        //val answwers =  answersList.workoutListGeneral.observeAsState(listOf())
        // ответы
        //for (answer in inquirerPage.answers)
        for (i in 0..inquirerPage.answers.size - 1) {
            val answer = inquirerPage.answers[i]
            Row(
                modifier = Modifier
                    .padding(
                        // horizontal = 16.dp
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                var checkedState by remember { mutableStateOf(inquirerPage.isChecked[i]) }
                CheckBoxWithTextRippleFullRow(
                    label = answer,
                    state = checkedState,
                    onStateChange = {
                        checkedState = it
                        //

                    }
                )

            }
        }
    }
}


@Composable
fun CheckBoxWithTextRippleFullRow(
    label: String,
    state: Boolean,
    onStateChange: (Boolean) -> Unit
) {
    // Checkbox with text on right side
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(20.dp)
        .clickable(
            role = Role.Checkbox,
            onClick = {
                // при клике на строку происходит изменение состояния чекбокса на противоположное
                onStateChange(!state)
            }
        )
        .padding(0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var checkedState by remember { mutableStateOf(state) }
        Checkbox(
            checked = state,
            colors = CheckboxDefaults.colors(
                checkedColor = colorScheme.primary,
                uncheckedColor = colorScheme.onBackground,
                checkmarkColor = colorScheme.surface

            ),
            onCheckedChange =
            {
                onStateChange(!state)
                //onStateChange
               // checkedState   = !checkedState
            }
        )
        Spacer(modifier = Modifier.width(0.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 8.dp
                ),
            color = colorScheme.onBackground,
            text = label,
            fontSize = 16.sp,
            //lineHeight = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,

            )

    }
}


@Composable
fun CheckboxInRowWithText() {
    val context = LocalContext.current
    var checkedState by remember { mutableStateOf(false) }
    Surface {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    Toast
                        .makeText(context, "Row clicked", Toast.LENGTH_SHORT)
                        .show()
                    checkedState = !checkedState
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = checkedState, onCheckedChange = {
                Toast.makeText(context, "Checkbox check", Toast.LENGTH_SHORT).show()
                checkedState = it
            }
            )
            Text(text = "Clicking this Row will toggle checkbox")
        }
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun InquirerBottomNav(
    inquirerViewModel: InquirerViewModel,
    navController: NavHostController,
    pagerState: PagerState,
    pagerCount: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(100.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    )
    {

           ReturnButton(
                modifier = Modifier

                    .height(50.dp),
                pagerState = pagerState,
                text = stringResource(R.string.back),

            ) {
                // к предыдущей странице приветствия
                if (pagerState.currentPage != 0)
                    GlobalScope.launch {
                        withContext(Dispatchers.Main) {
                            pagerState.scrollToPage(
                                pagerState.currentPage - 1,
                                pageOffset = 0f
                            )
                        }
                    }
                else {
                    //  c последней страницы онбордингп идём на главный экран БЕЗ ОНБОРДИНГА
                    //  и сохраняем флаг о том, что приветствие пройдено
                    inquirerViewModel.saveInquirerState(completed = true)
                    // онбоардинг пройден
                    isOnboardingPassedApp = true
                    navController.navigate(Screen.Home.route)
                    //navController.popBackStack()
                }
            }
        NextButton(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(50.dp),
            pagerState = pagerState,
            text = stringResource(R.string.continue_),
            pagerCount = pagerCount
        ) {

            //  c последней страницы приветствия идём на главный экран С ОНБОРДИНГОМ
            //  и сохраняем флаг о том, что онбординг пройдено
            if (pagerState.currentPage == pagerCount - 1) {
                //  онбординг пройден
                inquirerViewModel.saveInquirerState(completed = true)
                // onBoarding пройден
                isOnboardingPassedApp = true;
                //navController.navigate(Screen.Home.route)
                navController.navigate(Screen.Loader.route)
                //navController.popBackStack()
            }
            // к следующей странице приветствия
            else {
                GlobalScope.launch {
                    withContext(Dispatchers.Main) {
                        pagerState.scrollToPage(
                            pagerState.currentPage + 1,
                            pageOffset = 0f
                        )
                    }
                }
            }
        }
        }
    }


// кнопка "Вернуться"
// есть на всех экранах, кроме первого
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun ReturnButton(
    modifier: Modifier,
    text: String,
    pagerState: PagerState,
    onClick: () -> Unit
) {

        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(0.5f),
            visible = pagerState.currentPage > 0
        ) {
            Button(
                modifier = modifier
                    //.weight(0.5f)
                    // .fillMaxWidth()
                    .height(80.dp)
                    .width(80.dp)
                    .padding(
                        start = 16.dp,
                        end = 8.dp,
                        //bottom = 16.dp
                    ),
                onClick =
                {
                    onClick()
                },
                shape = CircleShape,
                border = BorderStroke(1.dp, colorScheme.onBackground),
                colors = ButtonDefaults.buttonColors(
                    //contentColor = colorScheme.surface,
                    backgroundColor = colorScheme.surface
                )
            ) {
                Text(
                    text = text,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.primary,
                )
            }
        }
    }



@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun NextButton(
    modifier: Modifier,
    pagerState: PagerState,
    pagerCount: Int,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(
                start = 8.dp,
                end = 16.dp,
                //bottom = 16.dp
            ),
        onClick =
    {
        onClick()
    },
        shape = CircleShape,
        //border= BorderStroke(1.dp, Color.Blue),
        colors = ButtonDefaults.buttonColors(
            contentColor = colorScheme.surface,
            backgroundColor = colorScheme.primary
        )
    ) {
        Text(
            modifier =  Modifier.align(Alignment.CenterVertically),
            text = text,
            fontWeight = FontWeight.Bold,
            color = colorScheme.background,
            textAlign = TextAlign.Center,  // horizontal center of the text
        )
    }
}


    @OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
    @Preview
    @Composable
    fun InquirerScreenWhitePrev() {
        VotTakVotTheme(
            darkTheme = false
        )
        {
            val context = LocalContext.current
            var inquirerViewModel = InquirerViewModel(DataStoreRepository(context))
            InquirerScreen(
                navController = rememberNavController(),
                inquirerViewModel = inquirerViewModel
            )
        }
    }

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Preview
@Composable
    fun InquirerScreenBlackPrev() {
        VotTakVotTheme(
            darkTheme = true)
        {
            val context =  LocalContext.current
            var inquirerViewModel = InquirerViewModel(DataStoreRepository(context))
            InquirerScreen(
                navController = rememberNavController(),
                inquirerViewModel = inquirerViewModel
            )
        }
    }
