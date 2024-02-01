@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.vottakvot.navigation.screens

import android.annotation.SuppressLint
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.database.WorkoutDataItem
import com.example.vottakvot.navigation.navigationLogic.Screen
import com.example.vottakvot.ui.theme.TrainTypeCard
import com.example.vottakvot.ui.theme.WorkoutCard
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// домашний экран после прохождения онбординга
// домашний экран

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    trainListForYou: TrainListViewModel,
    trainListPopular: MutableLiveData<List<WorkoutDataItem>>,
    isOnboardingPassed: Boolean = false,
    //isTrainListGets: MutableLiveData<Boolean>
) {
    // запрос отправлен
    var isResponseSend = mutableStateOf(false)

    // запрос отправлен
    var isResponse by remember {
        mutableStateOf(false)
    }
    // ответ получен
    var isAnswerGet = mutableStateOf(false)

    // загрузка популярных тренировок
    if ((trainListPopular == null) || (trainListPopular.value?.size ?: 0  == 0))
    {


        if (!isResponse) {
            FindPopularWorkouts(
                trainPopularList = trainListPopular
            )
            isResponse = true
        }
        //delay(100)



    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.87f)
            .background(colorScheme.background),
    ) {
        item {
            TrainsForYou(
                navController = navController,
                trainListForYou = trainListForYou,
                trainListPopular = trainListPopular,
                isOnboardingPassed = isOnboardingPassed,
                //isTrainListGets = isTrainListGets
            )
        }
    }
}


@Composable
private  fun TrainsForYou(
    navController: NavHostController,
    trainListForYou: TrainListViewModel,
    trainListPopular: LiveData<List<WorkoutDataItem>>,
    isOnboardingPassed: Boolean, // пройден ли онбординг
    //isTrainListGets: MutableLiveData<Boolean>
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
                //typeList = 0,
                //isTrainListGets = isTrainListGets,
                text = stringResource(R.string.workouts_for_you),
                textInstedTrainList = stringResource(R.string.trains_block_without_onboarding),
                isIconVisible = true,
                iconResId = Icons.Outlined.Tune,
                visibleCount = 3,
                isInternetAccess = true, //  есть ли доступ в интерне
                isOnboardingPassed = isOnboardingPassed, // пройден ли онбординг
                trainList = trainListForYou,
                //navigateTo = Screen.SearchResultForYou.route,
                withButton = true
         )
            // блок популярных тренировок
            TitleAndTrainListPopularBlock(
                navController = navController,
                //typeList = 1,
                //isTrainListGets = isTrainListGets,
                text = stringResource(R.string.popular),
                textInstedTrainList = stringResource(R.string.trains_block_without_internet),
                isIconVisible = false,
                iconResId = Icons.Outlined.Tune,
                visibleCount = 3,
                isInternetAccess = true, //  есть ли доступ в интерне
                isOnboardingPassed = true, // пройден ли онбординг
                trainListForYou = trainListForYou,
                trainListPopular = trainListPopular,
                //navigateTo = Screen.SearchResultPopular.route,
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
            navController = navController,
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
fun TitleAndTrainListBlock(
    navController: NavHostController,
    //isTrainListGets: MutableLiveData<Boolean>,
    text: String = stringResource(R.string.workouts_for_you),
    textInstedTrainList: String = stringResource(R.string.trains_block_without_onboarding),
    isIconVisible: Boolean = false,
    iconResId: ImageVector = Icons.Outlined.Tune,
    visibleCount: Int = 3,
    isInternetAccess: Boolean = true, //  есть ли доступ в интерне
    isOnboardingPassed: Boolean = true, // пройден ли онбординг
    trainList: TrainListViewModel,
    //trainListMy: TrainListViewModel,
    //rainListFavourite: TrainListViewModel,
    //navigateTo: String,
    withButton: Boolean = false
)
{
    // блок тренировок для вас
    // заголовок
    TitleBlock(
        navController = navController,
        text = text,
        isIconVisible = isIconVisible,
        iconResId =  iconResId
    )
    // список найденных тренировок для вас
    TrainsBlock(
        navController = navController,
        //typeList = typeList,
        //  есть ли доступ в интерне
        //isInternetAccess = isTrainListGets,
        // пройден ли онбординг
        isOnboardingPassed  = isOnboardingPassed,
        textInstedTrainList = textInstedTrainList,
        visibleCount = visibleCount,
        trainListViewModel = trainList,
        //trainListMy = trainListMy,
        //trainListFavourite = trainListFavourite,
        //navigateTo = navigateTo,
        withButton = withButton
    )
}

@Composable
fun TitleAndTrainListPopularBlock(
    navController: NavHostController,
    //isTrainListGets: MutableLiveData<Boolean>,
    text: String = stringResource(R.string.workouts_for_you),
    textInstedTrainList: String = stringResource(R.string.trains_block_without_onboarding),
    isIconVisible: Boolean = false,
    iconResId: ImageVector = Icons.Outlined.Tune,
    visibleCount: Int = 3,
    isInternetAccess: Boolean = true, //  есть ли доступ в интерне
    isOnboardingPassed: Boolean = true, // пройден ли онбординг
    trainListForYou: TrainListViewModel,
    trainListPopular: LiveData<List<WorkoutDataItem>>,
    withButton: Boolean = false
)
{
    // блок тренировок для вас
    // заголовок
    TitleBlock(
        navController = navController,
        text = text,
        isIconVisible = isIconVisible,
        iconResId =  iconResId
    )
    // список найденных тренировок для вас
    TrainPopularBlock(
        navController = navController,
        //typeList = typeList,
        //  есть ли доступ в интерне
        //isInternetAccess = isTrainListGets,
        // пройден ли онбординг
        //isOnboardingPassed  = isOnboardingPassed,
        textInstedTrainList = textInstedTrainList,
        trainListPopular = trainListPopular,
        trainListForYou = trainListForYou,

        visibleCount =  visibleCount,
        //navigateTo = navigateTo,
        withButton = withButton
    )
}



// заголовок
@Composable
fun TitleBlock(
    navController: NavHostController,
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
                navController = navController,
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
private  fun TrainPopularBlock(
    navController: NavHostController,
    //isInternetAccess: MutableLiveData<Boolean>,//  есть ли доступ в интерне
    textInstedTrainList: String,
    visibleCount: Int = 3,
    trainListForYou: TrainListViewModel,
    trainListPopular: LiveData<List<WorkoutDataItem>>,
    navigateTo: String = Screen.SearchResultForYou.route, // куда переходим с этой страницы,
    withButton: Boolean = false
)
{
    val notNullList = (trainListPopular.value != null)
        //?.size != 0
        //trainListForYou.workoutListGeneral.value?.size != 0
    //if (isOnboardingPassed && notNullList) {
    if (notNullList) {
        //(isInternetAccess.value == true)) {
        // источник данных для списк тренировок
        TrainsBlockPopularWithOnBoardingAndInternet(
           // typeList = typeList,
            visibleCount = visibleCount, // кол-во видимых тренировок
            trainListForYou = trainListForYou ,
            trainListPopular = trainListPopular,
            //trainListMy = trainListMy,
            //trainListFavourite = trainListFavourite,
            navController = navController
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
        navController = navController,
        text = textInstedTrainList,
        withButton = withButton
    )
}


/*
// список найденных тренировок
@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
private  fun TrainsBlockPopular(
    navController: NavHostController,
    typeList: Int = 0,
    isInternetAccess: MutableLiveData<Boolean>,//  есть ли доступ в интерне
    isOnboardingPassed: Boolean = true, // пройден ли онбординг
    textInstedTrainList: String,
    visibleCount: Int = 3,
    trainListPopular : TrainListViewModel,
    trainListMy: TrainListViewModel,
    trainListFavourite: TrainListViewModel,
    navigateTo: String = Screen.SearchResultForYou.route, // куда переходим с этой страницы,
    withButton: Boolean = false
)
{
    val u = trainListPopular.workoutListGeneral.value?.size != 0
    isInternetAccess.postValue(u)
    //  если онбординг пройден - отображать "Тренировки для Вас"
    val notNullList = trainListPopular.workoutListGeneral.value?.size != 0
    //if (isOnboardingPassed && notNullList) {
    if (notNullList) {
        //(isInternetAccess.value == true)) {
        // источник данных для списк тренировок
        TrainsBlockWithOnBoardingAndInternet(
            visibleCount = visibleCount, // кол-во видимых тренировок
            trainList =
            navController = navController
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
        navController = navController,
        text = textInstedTrainList,
        withButton = withButton
    )
}
*/

// список найденных тренировок
@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
private  fun TrainsBlock(
    navController: NavHostController,
    //typeList: Int = 0,
    //isInternetAccess: MutableLiveData<Boolean>,//  есть ли доступ в интерне
    isOnboardingPassed: Boolean = true, // пройден ли онбординг
    textInstedTrainList: String,
    visibleCount: Int = 3,
    trainListViewModel : TrainListViewModel,
    //navigateTo: String = Screen.SearchResultForYou.route, // куда переходим с этой страницы,
    withButton: Boolean = false
)
{

    val yourTrains = trainListViewModel
        .workoutListGeneral
        .observeAsState(listOf())

    val yourExercises = trainListViewModel
        .exerciseListGeneral
        .observeAsState(listOf())

    //val u = trainListViewModel.workoutListGeneral.value?.size != 0
   // isInternetAccess.postValue(u)
    //  если онбординг пройден - отображать "Тренировки для Вас
        //?.size
    //!= 0
    //if (isOnboardingPassed && notNullList) {
    if ((yourTrains.value != null) && (yourTrains.value.size != 0)) {


        if ((yourExercises.value != null) && (yourExercises.value.size != 0)) {
                // сформировать список тренировок
                trainListViewModel.makeExerciseList()
            }

            //(isInternetAccess.value == true)) {
            // источник данных для списк тренировок
            TrainsBlockWithOnBoardingAndInternet(
                visibleCount = visibleCount, // кол-во видимых тренировок
                trainList = trainListViewModel,
                navController = navController
            )
            MoreTrainButton(
                modifier = Modifier
                    .fillMaxWidth(1f),
                text = stringResource(R.string.more_trains),
            ) {
                GlobalScope.launch {
                    withContext(Dispatchers.Main) {
                        // переходим на страницу поиска тренировок для Вас
                        navController.navigate(Screen.SearchResultForYou.route)
                        // к предыдущей странице приветствия
                    }

                }
            }
        }
    //  если не пройден - предложить пройти
    else  TrainsBlockWithoutOnBoarding(
        navController = navController,
        text = textInstedTrainList,
        withButton = withButton
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
   // typeList: Int = 0, // 0 - ForU 1 - Popular
    visibleCount: Int = 3, // кол-во видимых тренировок

    trainList: TrainListViewModel,
    //trainListFavourite: TrainListViewModel,
    //trainListForYou: TrainListViewModel,
    //trainListMy: TrainListViewModel,
    navController: NavHostController
) {
    val yourTrains = trainList
        .workoutListGeneral
        .observeAsState(listOf())



    //val yourTrains = trainList.workoutListGeneral.observeAsState(listOf())
    val minCount = Math.min(yourTrains.value.size, visibleCount)
    if (minCount >= 1) {
        for (i in 0..minCount - 1) {
            var workoutItem = yourTrains.value[i]
            WorkoutCard(
                //workoutViewModel = workoutViewModel,
                workoutItem = workoutItem,
                // клик по карточке
                onCardClickListener = {

                    GlobalScope.launch {
                        withContext(Dispatchers.Main) {
                            trainList.currentWorkoutId = it.id
                            navController.navigate(Screen.Workout.route)
                        }

                    }

                    // id текущег упражнения

                    // переход на страницу упражнения
                     /*WorkoutScreen(
                       navController = navController ,
                         trainList = trainList
                    )*/


                },
                // при добавлении в мои тренировки
                onAddedClickListener = {
                    // если тренировки нет в списке моих тренировок
                    if (!it.isAddedToMyTrainList) {
                        // добавляем в мои тренировки как новую тренировку
                        it.id += 3000
                        it.isAddedToMyTrainList = true
                        trainList.insertOneWorkoutWithExercise(it)
                        // добавить упражнения к этой тренировке
                    }
                    else
                    {
                        trainList.changeAddedStatusList(it)
                    }



                    //trainList.changeAddedStatusList(it)
                    //trainListMy.changeAddedStatusList(it)
                },
                onLikeClickListener = {
                    //it.isAddedToFavourite= true
                    trainList.changeLikedStatusList(it)
                    //trainListFavourite.changeLikedStatusList(it)
                },
                onPlayClickListener = {
                    //trainListForYou.changePlayingStatusList(it)
                }
            )
        }
    }
}

// блок отображающий 3 видимые тренировки для вас или 3 видимые популярные тренировки
@Composable
private  fun TrainsBlockPopularWithOnBoardingAndInternet (
    // typeList: Int = 0, // 0 - ForU 1 - Popular
    visibleCount: Int = 3, // кол-во видимых тренировок
    trainListForYou: TrainListViewModel,
    trainListPopular: LiveData<List<WorkoutDataItem>>,
    navController: NavHostController
) {
    //val yourTrains = trainListPopular //trainList.workoutListGeneral.observeAsState(listOf())
    if (trainListPopular.value == null)
        return
    val minCount = Math.min(trainListPopular.value!!.size, visibleCount)
    if (minCount >= 1) {
        for (i in 0..minCount - 1) {
            var workoutItem = trainListPopular.value?.get(i)
            if (workoutItem != null) {
                WorkoutCard(
                    //workoutViewModel = workoutViewModel,
                    workoutItem = workoutItem,
                    // клик по карточке
                    onCardClickListener = {
                        // id текущег упражнения
                        // trainListForYou.currentWorkoutId = it.id
                        // переход на страницу упражнения
                        /*WorkoutScreen(
                              navController = navController ,
                                trainList = trainList
                           )*/
                        navController.navigate(Screen.Workout.route)
                        /*when (typeList) {
                                0 -> navController.navigate(Screen.WorkoutForU.route)
                                1 -> navController.navigate(Screen.WorkoutPopular.route)
                                else -> navController.navigate(Screen.WorkoutForU.route)
                            }*/

                    },
                    // слушатели клика
                    onAddedClickListener = {
                        //it.isAddedToMyTrainList = true
                        trainListForYou.changeAddedStatusList(it)
                        //trainListMy.changeAddedStatusList(it)
                    },
                    onLikeClickListener = {
                        //it.isAddedToFavourite = true
                        trainListForYou.changeLikedStatusList(it)
                        //trainListFavourite.changeLikedStatusList(it)
                    },
                    onPlayClickListener = {
                        //trainListForYou.changePlayingStatusList(it)
                    }
                )
            }
        }
    }
}


// как вышлдит домашнй экран у пользователя,
// не прошедшего onBoarding
@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun TrainsBlockWithoutOnBoarding(
    navController: NavHostController,
    text: String = stringResource(R.string.trains_block_without_internet),
    withButton: Boolean = false
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
     text = text,
     color = colorScheme.onBackground,
     fontSize = 16.sp,
     //fontWeight = FontWeight.Medium,
     textAlign = TextAlign.Justify,
     lineHeight = 25.sp
 )
    if (withButton) {
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
    navController: NavHostController,
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
            ),
           // .clickable {
               // onItemClickListener()
           // },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            modifier = modifier
                .clip(CircleShape)
                .clickable {
                    navController.navigate(Screen.Filter.route,)
                }
            ,
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


/*
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
        val isTrainListGets0: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    HomeScreen(
        navController = navController,
        trainListForYou = trainListForYou,
        trainListPopular = trainListPopular,
        isTrainListGets = isTrainListGets0
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
        val isTrainListGets0: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
        HomeScreen(
            navController = navController,
            trainListForYou = trainListForYou,
            trainListPopular = trainListPopular,
            isTrainListGets = isTrainListGets0
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
*/


