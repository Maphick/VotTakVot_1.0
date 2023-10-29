@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.vottakvot.navigation.screens

import android.icu.text.CaseMap.Title
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.domain.WorkoutDataItem
import com.example.vottakvot.isOnboardingPassedApp
import com.example.vottakvot.navigation.navigationLogic.Screen
import com.example.vottakvot.ui.theme.TrainTypeCard
import com.example.vottakvot.ui.theme.VotTakVotTheme
import com.example.vottakvot.ui.theme.WorkoutCard
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sourceListPopularExample
import sourceListTrainsForYouExample

// домашний экран после прохождения онбординга
// домашний экран

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    trainListForYou: TrainListViewModel,
    trainListPopular: TrainListViewModel,
    isOnboardingPassed: Boolean = false
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .background(colorScheme.background),
    ) {
        item {
            TrainsForYou(
                navController = navController,
                trainListForYou = trainListForYou,
                trainListPopular = trainListPopular,
                isOnboardingPassed = isOnboardingPassed
            )
        }
    }
}


@Composable
private  fun TrainsForYou(
    navController: NavHostController,
    trainListForYou: TrainListViewModel,
    trainListPopular: TrainListViewModel,
    isOnboardingPassed: Boolean // пройден ли онбординг
)
{
    Box(
        modifier = Modifier
            .background(colorScheme.background)
    )
    {
        Column(
        ) {
            // блок тренировок для вас
            TitleAndTrainListBlock(
                navController = navController,
                text = stringResource(R.string.workouts_for_you),
                isIconVisible = true,
                iconResId = Icons.Outlined.Tune,
                visibleCount = 3,
                isInternetAccess = true, //  есть ли доступ в интерне
                isOnboardingPassed = isOnboardingPassed, // пройден ли онбординг
                trainListViewModel = trainListForYou,
                navigateTo = Screen.SearchResultForYou.route
         )
            // блок популярных тренировок
            TitleAndTrainListBlock(
                navController = navController,
                text = stringResource(R.string.popular),
                isIconVisible = false,
                iconResId = Icons.Outlined.Tune,
                visibleCount = 3,
                isInternetAccess = true, //  есть ли доступ в интерне
                isOnboardingPassed = true, // пройден ли онбординг
                trainListViewModel = trainListPopular,
                navigateTo = Screen.SearchResultPopular.route
       )

            // блок типовтренировок
            TrainTypeBlock(
                navController = navController,
                title = stringResource(R.string.train_type)
            )
            }

        }
    }

@Composable
fun TrainTypeBlock(
    navController: NavHostController,
    navigateFromCharger: String = Screen.SearchResultCharger.route,
    navigateFromHomeFitness: String = Screen.SearchResultHomeFitness.route,
    navigateFromWorkFitness: String = Screen.SearchResultWorkFitness.route,
    navigateFromBeforeBedtime: String = Screen.SearchResultBeforeBedtime.route,
    title: String = stringResource(R.string.train_type)
)
{
   Column(
    modifier = Modifier
    .fillMaxWidth()
   )
    {
        val modifier: Modifier = Modifier
            .padding(
                4.dp
            )

        // блок тренировок для вас
        // заголовок
        TitleBlock(
            text = title,
            isIconVisible = false
        )
       Row(
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.SpaceBetween
       )
       {
           TrainTypeCard(
               modifier = modifier
                   .fillMaxWidth(0.5f),
               title = stringResource(R.string.charger),
               painterResourceId = R.drawable.charger,
               onLCardClickListener = {
                   GlobalScope.launch {
                       withContext(Dispatchers.Main) {
                           // переходим на страницу поиска тренировок для Вас
                           navController.navigate(navigateFromCharger)
                           // к предыдущей странице приветствия
                       }

                   }
               }
           )
           TrainTypeCard(
               modifier = modifier
                   .fillMaxWidth(),
                   title = stringResource(R.string.home_fitness),
           painterResourceId = R.drawable.home_fitness,
               onLCardClickListener = {
                   GlobalScope.launch {
                       withContext(Dispatchers.Main) {
                           // переходим на страницу поиска тренировок для Вас
                           navController.navigate(navigateFromHomeFitness)
                           // к предыдущей странице приветствия
                       }

                   }
               }
           )
       }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        )
        {
            TrainTypeCard(
                modifier = modifier
                    .fillMaxWidth(0.5f),
                title = stringResource(R.string.work_fitness),
                painterResourceId = R.drawable.work_fitness,
                onLCardClickListener = {
                    GlobalScope.launch {
                        withContext(Dispatchers.Main) {
                            // переходим на страницу поиска тренировок для Вас
                            navController.navigate(navigateFromWorkFitness)
                            // к предыдущей странице приветствия
                        }

                    }
                }
            )
            TrainTypeCard(
                modifier = modifier
                    .fillMaxWidth(1f),
                title = stringResource(R.string.before_bedtime),
                painterResourceId = R.drawable.before_bedtime,
                onLCardClickListener = {
                    GlobalScope.launch {
                        withContext(Dispatchers.Main) {
                            // переходим на страницу поиска тренировок для Вас
                            navController.navigate(navigateFromBeforeBedtime)
                            // к предыдущей странице приветствия
                        }

                    }
                }
            )

        }

   }
}


@Composable
private fun TitleAndTrainListBlock(
    navController: NavHostController,
    text: String = stringResource(R.string.workouts_for_you),
    isIconVisible: Boolean = false,
    iconResId: ImageVector = Icons.Outlined.Tune,
    visibleCount: Int = 3,
    isInternetAccess: Boolean = true, //  есть ли доступ в интерне
    isOnboardingPassed: Boolean = true, // пройден ли онбординг
    trainListViewModel: TrainListViewModel,
    navigateTo: String
    )
{
        // блок тренировок для вас
        // заголовок
        TitleBlock(
            text = text,
            isIconVisible = isIconVisible,
            iconResId =  iconResId
        )
       // список найденных тренировок для вас
        TrainsBlock(
            navController = navController,
            visibleCount = visibleCount,
            isInternetAccess = isInternetAccess, //  есть ли доступ в интерне
            isOnboardingPassed  = isOnboardingPassed, // пройден ли онбординг
            trainListViewModel = trainListViewModel,
            navigateTo = navigateTo
        )
}




// заголовок
@Composable
private fun TitleBlock(
    text: String = stringResource(R.string.workouts_for_you),
    isIconVisible: Boolean = false,
    iconResId: ImageVector = Icons.Outlined.Tune
)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(
                start = 8.dp
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Text(
            text = text,
            fontSize = 30.sp,
            //fontWeight = FontWeight.Bold,
            color = colorScheme.onBackground,
        )
        if (isIconVisible) {
            IconFilterButton(
                modifier = Modifier
                    .size(
                        35.dp
                    )
                    .padding(
                        top = 0.dp
                    ),
                iconResId = iconResId,
                iconResIdPressed = iconResId,
                isChanged = true
            )
            {
                //onAddedClickListener(workoutItem)
            }
        }
    }
}

// список найденных тренировок
@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
private  fun TrainsBlock(
    navController: NavHostController,
    visibleCount: Int = 3,
    isInternetAccess: Boolean = true, //  есть ли доступ в интерне
    isOnboardingPassed: Boolean = true, // пройден ли онбординг
    trainListViewModel : TrainListViewModel,
    navigateTo: String = Screen.SearchResultForYou.route // куда переходим с этой страницы
)
{
    //  если онбординг пройден - отображать "Тренировки для Вас"
    if (isOnboardingPassed && isInternetAccess) {
        // источник данных для списк тренировок
        TrainsBlockWithOnBoardingAndInternet(
            visibleCount = visibleCount, // кол-во видимых тренировок
            trainList = trainListViewModel
        )
        MoreTrainButton(
            modifier = Modifier
                .fillMaxWidth(1f),
            text = stringResource(R.string.more_trains),
        ) {
            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    // переходим на страницу поиска тренировок для Вас
                    navController.navigate(navigateTo)
                    // к предыдущей странице приветствия
                }

            }
        }
    }
    //  если не пройден - предложить пройти
    else  TrainsBlockWithoutOnBoarding(
        navController = navController
    )
}

//  кнопка "Больше тренировок... "
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun MoreTrainButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Start
    ) {
            OutlinedButton(
                modifier = modifier,
                border = BorderStroke(0.dp, Color.Transparent),
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = colorScheme.onSecondaryContainer,
                    backgroundColor = Color.Transparent
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

// блок отображающий 3 видимые тренировки для вас или 3 видимые популярные тренировки
@Composable
private  fun TrainsBlockWithOnBoardingAndInternet (
    visibleCount: Int = 3, // кол-во видимых тренировок
    trainList : TrainListViewModel
) {
    val yourTrains = trainList.workoutListGeneral.observeAsState(listOf())
    for (i in 0..visibleCount - 1) {
        var workoutItem = yourTrains.value[i]
        WorkoutCard(
            //workoutViewModel = workoutViewModel,
            workoutItem = workoutItem,
            // слушатели клика
            onAddedClickListener = {
                trainList.changeAddedStatusList(it)
            },
            onLikeClickListener = {
                trainList.changeLikedStatusList(it)
            },
            onPlayClickListener = {
                trainList.changePlayingStatusList(it)
            }
        )
    }
}


// как вышлдит домашнй экран у пользователя,
// не прошедшего onBoarding
@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
private  fun TrainsBlockWithoutOnBoarding(
    navController: NavHostController
)
{
 Text(
     modifier = Modifier
         .fillMaxWidth(1f)
         .padding(
             top = 24.dp,
             start = 32.dp,
             end = 32.dp,
             bottom = 24.dp
         ),
     text = stringResource(R.string.trains_block_without_onboarding),
     color = colorScheme.onBackground,
     fontSize = 16.sp,
     //fontWeight = FontWeight.Medium,
     textAlign = TextAlign.Justify,
     lineHeight = 25.sp
 )
    // кнопка отправлющая снова пройти онбординг
    TrainSelectionButton(
        navController = navController,
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(80.dp),
        text = stringResource(R.string.train_selection),
    ) {
        // переходим на экран онбординга
            navController.navigate(Screen.Inquirer.route)
    }
}


// подбор тренировок в случае, если пользоватеь не прошел онбординг
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun TrainSelectionButton(
    navController: NavHostController,
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp,
                top = 16.dp
                //bottom = 16.dp
            ),
        onClick =
        {
            onClick()
        },
        shape = CircleShape,
        //border= BorderStroke(1.dp, Color.Blue),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorScheme.secondaryContainer
        )
    ) {
        Text(
            modifier =  Modifier.align(Alignment.CenterVertically),
            text = text,
            fontWeight = FontWeight.Bold,
            color = colorScheme.onSecondaryContainer,
            textAlign = TextAlign.Center,  // horizontal center of the text
        )
    }
}


@Composable
private fun IconFilterButton(
    modifier: Modifier = Modifier,
    iconResId: ImageVector,
    iconResIdPressed: ImageVector,
    isChanged: Boolean,
    onItemClickListener: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                end = 8.dp
            )
            .clickable {
                onItemClickListener()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            modifier = modifier,
            imageVector = if (isChanged) {
                iconResIdPressed
            } else {
                iconResId
            },
            contentDescription = null,
            tint = if (isChanged) {
                colorScheme.onSurfaceVariant
            } else {
                colorScheme.onSurface
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreenWithOnboardingWhitePrev() {
    VotTakVotTheme(
        darkTheme = false)
    {
    val context = LocalContext.current
    val trainListForYou: TrainListViewModel =
        TrainListViewModel(source = sourceListTrainsForYouExample)
    val trainListPopular: TrainListViewModel =
        TrainListViewModel(source = sourceListPopularExample)
    val navController = NavHostController(context = context)
    HomeScreen(
        navController = navController,
        trainListForYou = trainListForYou,
        trainListPopular = trainListPopular
    )
    }
}


@Preview
@Composable
fun HomeScreenWithOnboardingBlackPrev() {
    VotTakVotTheme(
        darkTheme = true)
    {
        val context = LocalContext.current
        val trainListForYou: TrainListViewModel =
            TrainListViewModel(source = sourceListTrainsForYouExample)
        val trainListPopular: TrainListViewModel =
            TrainListViewModel(source = sourceListPopularExample)
        val navController = NavHostController(context = context)
        HomeScreen(
            navController = navController,
            trainListForYou = trainListForYou,
            trainListPopular = trainListPopular
        )
    }
}

@Preview
@Composable
fun TrainTypeBlockLightPrev() {
    VotTakVotTheme(
        darkTheme = false)
    {
        val context = LocalContext.current
        val navController = NavHostController(context = context)
        TrainTypeBlock(
            navController = navController,
            title = stringResource(R.string.train_type)
        )
    }
}
@Preview
@Composable
fun TrainTypeBlockBlackPrev() {
    VotTakVotTheme(
        darkTheme = true)
    {
        val context = LocalContext.current
        val navController = NavHostController(context = context)
        TrainTypeBlock(
            navController = navController,
            title = stringResource(R.string.train_type)
        )
    }
}



