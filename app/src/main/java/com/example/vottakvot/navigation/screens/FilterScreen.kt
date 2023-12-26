package com.example.vottakvot.navigation.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.PlaylistAdd
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.InquirerViewModel
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.data.DataStoreRepository
import com.example.vottakvot.internet.getAllTrains
import com.example.vottakvot.internet.getYourTrains
import com.example.vottakvot.navigation.navigationLogic.Screen
import com.example.vottakvot.ui.theme.IcoButton
import com.example.vottakvot.ui.theme.VotTakVotTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import sourceListSearchResultExample


//  Экран с фильтрами
@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun FilterScreen(
    navController: NavHostController,
    title: String,
    trainList: TrainListViewModel,
    inquirerViewModel: InquirerViewModel,
) {
    var keyWord = "Все"
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .background(MaterialTheme.colorScheme.background)
    ) {
        filterHeaderBlock(
            navController = navController,
            title = title
        )

        //val context =  LocalContext.current
        //var inquirerViewModel = in
            //InquirerViewModel(DataStoreRepository(context))
        //InquirerDataGeneration(inquirerViewModel)
        var pages = inquirerViewModel.getInquirerPagesList()
        var filterName = stringResource(R.string.type_train)
        var filterValues = pages[0].answers
        TypeTrainBlock(
            filterName = filterName,
            filterValues = filterValues,
            inquirerViewModel = inquirerViewModel,
            )
        {
            inquirerViewModel.setSelectedWorkoutType(it)
        }
        filterName = stringResource(R.string.muscle_groups)
        filterValues = pages[1].answers
        Spacer(modifier = Modifier.height(20.dp))
        MuscleGroupsBlock(
            filterName = filterName,
            filterValues = filterValues,
            inquirerViewModel = inquirerViewModel,
        )
        {
            // поиск по слову
            inquirerViewModel.setSelectedWorkoutType(it)
            keyWord = it
        }
        Spacer(modifier = Modifier.height(20.dp))
        SearchWorkoutsButton(
            text = stringResource(R.string.find_workouts)
        )
        {
            inquirerViewModel.setKeyWord(keyWord)
            // переход на страницу с результатами
            navController.navigate(Screen.FindWorkouts.route)



        }
    }
}



fun FindWorkouts(
    inquirerViewModel: InquirerViewModel,
    trainListForYou: TrainListViewModel
)
{
    var getSuccessed = getYourTrains(
        inquirerViewModel = inquirerViewModel,
        limit = 100,
        trainListSearched =  trainListForYou
    )
}


fun FindYoursWorkouts(
    inquirerViewModel: InquirerViewModel,
    trainListSearched: TrainListViewModel,
    //keyWord: String
)
{
   // var getSuccessed = inquirerViewModel.keyWord.value?.let {
        getYourTrains(
        inquirerViewModel = inquirerViewModel,
        limit = 100,
        trainListSearched =  trainListSearched,
       // keyWord = it
    )
   // }
}

fun FindPopularWorkouts(
    trainListPopular: TrainListViewModel
)
{
    var getSuccessed = getAllTrains(
        limit = 1000,
        trainListPopular = trainListPopular
    )
}

@Composable
fun filterHeaderBlock(
    navController: NavHostController,
    title: String
)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(
                //start = 8.dp
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Text(
            modifier = Modifier
                .padding(8.dp),
            text = title,
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.onBackground,
        )
        IconCloseButton(
            modifier = Modifier
                .clip(CircleShape)
                .size(
                    35.dp
                )
                .padding(
                    top = 0.dp
                ),
            iconResId = Icons.Outlined.PlaylistAdd,
            iconResIdPressed = Icons.Outlined.Close,
            isChanged = true
        )
        {
            // закрыть окно с поиском
            navController.popBackStack()
            //onAddedClickListener(workoutItem)
        }
    }
}

@Composable
fun ClearButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = Modifier
            .height(40.dp),
        onClick = {
            onClick()
                  },
        border = BorderStroke(1.dp, Color.Transparent),
        //shape = RoundedCornerShape(50), // = 50% percent
        // or shape = CircleShape

    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}


@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun TrainFilterButton(
    //navController: NavHostController,
    modifier: Modifier = Modifier,
    text: String,
    selectedItem: String,
    onClick: () -> Unit
) {

    Button(
        modifier = modifier
            .wrapContentWidth()
            .height(50.dp)
            .padding(
                start = 8.dp,
                end = 0.dp,
                bottom = 0.dp,
                top = 8.dp
                //bottom = 16.dp
            ),
        onClick =
        {
            onClick()
        },
        shape = RoundedCornerShape(20),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onError),
        //border= BorderStroke(1.dp, Color.Blue),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (text == selectedItem)
                MaterialTheme.colorScheme.inverseSurface
                       else MaterialTheme.colorScheme.primaryContainer
            )
    ) {
        Text(
            modifier =  Modifier.align(Alignment.CenterVertically),
            text = text,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            textAlign = TextAlign.Center,  // horizontal center of the text
            maxLines = 1
        )
    }
}


@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun TypeTrainBlock(
    //navController: NavHostController,
    filterName: String,
    filterValues: List<String>,
    inquirerViewModel: InquirerViewModel,
    selectWorkoutType: (String) -> Unit
    //selectedItemIndex: muta
    //inquirerViewModel: InquirerViewModel,
) {

    var listItems = mutableListOf<String>()
    listItems = filterValues.toMutableList()
    listItems.add("Все")
    var selectedItem by rememberSaveable { mutableStateOf(listItems[4]) }
    Column()
    {

        Row(
            horizontalArrangement = Arrangement.Start
        )
        {
           Row(horizontalArrangement = Arrangement.Start)
           {
               SearchStringButton(
                   text = stringResource(R.string.find_by_name)
               ) {

               }
           }
            Row(horizontalArrangement = Arrangement.Start)
            {

            }
        }
        Row(
            modifier = Modifier
                //.background(Color.Green)
                .padding(
                    start = 10.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
        )
        {
            Row(
                modifier = Modifier
                    //.background(Color.Red)
                ,
                verticalAlignment = Alignment.CenterVertically,
            )
            {
                Text(
                    textAlign = TextAlign.Start,
                    text = filterName,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                //.background(Color.Blue),
                horizontalArrangement = Arrangement.End
            )
            {
                ClearButton(text = "Очистить")
                {
                    selectedItem = listItems[listItems.size-1]
                    inquirerViewModel.setSelectedWorkoutType(selectedItem)
                    selectWorkoutType(selectedItem)
                }
            }
        }

        Row(
        )
        {
            TrainFilterButton(
                text = listItems[0],
                selectedItem = selectedItem
            )
            {
                selectedItem = listItems[0]
                inquirerViewModel.setSelectedWorkoutType(selectedItem)
                selectWorkoutType(selectedItem)
            }
            TrainFilterButton(
                text = listItems[1],
                selectedItem = selectedItem
            )
            {
                selectedItem = listItems[1]
                inquirerViewModel.setSelectedWorkoutType(selectedItem)
                selectWorkoutType(selectedItem)
            }
        }
        Row()
        {
            TrainFilterButton(
                text = listItems[2],
                selectedItem = selectedItem
            )
            {
                selectedItem = listItems[2]
                inquirerViewModel.setSelectedWorkoutType(selectedItem)
                selectWorkoutType(selectedItem)
            }
        }
        Row()
        {
            TrainFilterButton(
                text = listItems[3],
                selectedItem = selectedItem
            )
            {
                selectedItem = listItems[3]
                inquirerViewModel.setSelectedWorkoutType(selectedItem)
                selectWorkoutType(selectedItem)
            }
        }
    }
}


@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun MuscleGroupsBlock(
    //navController: NavHostController,
    filterName: String,
    filterValues: List<String>,
    inquirerViewModel: InquirerViewModel,
    selectWorkoutType: (String) -> Unit
    //selectedName: LiveData<String>
) {
    val listItems = listOf(
        stringResource(R.string.full_body),
        stringResource(R.string.upper_body),
        stringResource(R.string.botttom_body),
        stringResource(R.string.abd),
        stringResource(R.string.all_variants)
    )
    var selectedItem by rememberSaveable { mutableStateOf(listItems[4]) }
    Column()
    {
        Row(
            modifier = Modifier
                //.background(Color.Green)
                .padding(
                    start = 10.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
        )
        {
            Row(
                modifier = Modifier
                //.background(Color.Red)
                ,
                verticalAlignment = Alignment.CenterVertically,
            )
            {
                Text(
                    textAlign = TextAlign.Start,
                    text = filterName,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                //.background(Color.Blue),
                horizontalArrangement = Arrangement.End
            )
            {
                ClearButton(text = "Очистить"){
                    selectedItem = listItems[listItems.size-1]
                    inquirerViewModel.setSelectedWorkoutType(selectedItem)
                    selectWorkoutType(selectedItem)
                }
            }
        }
        Row(
        )
        {
            TrainFilterButton(
                text = listItems[0],
                selectedItem = selectedItem
            )
            {
                selectedItem = listItems[0]
                inquirerViewModel.setSelectedWorkoutType(selectedItem)
                selectWorkoutType(selectedItem)
            }
            TrainFilterButton(
                text = listItems[1],
                selectedItem = selectedItem
            )
            {
                selectedItem = listItems[1]
                inquirerViewModel.setSelectedWorkoutType(selectedItem)
                selectWorkoutType(selectedItem)
            }
        }
        Row(
        )
        {
            Row()
            {
                TrainFilterButton(
                    text = listItems[2],
                    selectedItem = selectedItem
                )
                {
                    selectedItem = listItems[2]
                    inquirerViewModel.setSelectedWorkoutType(selectedItem)
                    selectWorkoutType(selectedItem)
                }
            }
            Row()
            {
                TrainFilterButton(
                    text = listItems[3],
                    selectedItem = selectedItem
                )
                {
                    selectedItem = listItems[3]
                    inquirerViewModel.setSelectedWorkoutType(selectedItem)
                    selectWorkoutType(selectedItem)
                }
            }
        }
    }
}

// поисковая строка
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SearchStringButton(
    //navController: NavHostController,
    //modifier: Modifier,
    text: String,
    onClick: () -> Unit
) {

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(
                start = 8.dp,
                end = 8.dp,
                //bottom = 16.dp,
                //top = 16.dp
                //bottom = 16.dp
            ),
        onClick =
        {
            onClick()
        },
        shape = CircleShape,
        //border= BorderStroke(1.dp, Color.Blue),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                //background(Color.Red)
                .fillMaxWidth(1f)
            ,
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Row(
                modifier = Modifier
                    //.background(Color.Red)
                    .width(200.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = text,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlign = TextAlign.Start,  // horizontal center of the text
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                    //.background(Color.Blue),
                horizontalArrangement = Arrangement.End
            )
            {
                IcoButton(
                    modifier = Modifier
                        .size(35.dp)
                        .padding(
                            //top = 8.dp
                        ),
                    iconResId = Icons.Outlined.Search,
                    iconResIdPressed = Icons.Outlined.Search,
                    isChanged = true
                )
                {

                }
            }
        }
    }
}

// найти тренировки
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SearchWorkoutsButton(
    //navController: NavHostController,
    //modifier: Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
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
            backgroundColor = MaterialTheme.colorScheme.primary
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
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = text,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.background,
                    textAlign = TextAlign.Start,  // horizontal center of the text
                )
                Icon(
                    modifier = Modifier
                        .padding(start = 8.dp)
                    ,
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background
                )
        }
    }
}


@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Preview
@Composable
fun FilterBlockWhitePrev() {
    VotTakVotTheme(
        darkTheme = false
    )
    {
        val context =  LocalContext.current
        var inquirerViewModel = InquirerViewModel(DataStoreRepository(context))
        InquirerDataGeneration(inquirerViewModel)
        var pages = inquirerViewModel.getInquirerPagesList()
        var filterValues = pages[0].answers
        TypeTrainBlock(
            filterName = "Тип тренировки",
            filterValues = filterValues,
            inquirerViewModel = inquirerViewModel,
            )
        {
            inquirerViewModel.setSelectedWorkoutType(it)
        }
        filterValues = pages[1].answers

        /*
        MuscleGroupsBlock(
            filterName = "Группы мышц:",
            filterValues = filterValues
            //inquirerViewModel: InquirerViewModel,
        )
        {

        }
        */


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun FilterScreenWhitePrev() {
    VotTakVotTheme(
        darkTheme = false)
    {
        val context = LocalContext.current
        val navController = NavHostController(context = context)
        val title = stringResource(R.string.find_workouts)
        val trainList: TrainListViewModel =
            TrainListViewModel(source = sourceListSearchResultExample)
        FilterScreen (
            navController = navController,
            title = title,
            trainList = trainList,
            inquirerViewModel = InquirerViewModel(DataStoreRepository(context)),

        )
    }
}


@Preview
@Composable
fun FilterScreenBlackPrev() {
    VotTakVotTheme(
        darkTheme = true)
    {
        val context = LocalContext.current
        val navController = NavHostController(context = context)
        val title = stringResource(R.string.find_workouts)
        val trainList: TrainListViewModel =
            TrainListViewModel(source = sourceListSearchResultExample)
        FilterScreen (
            navController = navController,
            title = title,
            trainList = trainList,
            inquirerViewModel = InquirerViewModel(DataStoreRepository(context))
        )
    }
}

