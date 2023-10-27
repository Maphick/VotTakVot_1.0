package com.example.vottakvot.navigation.screens

import androidx.compose.foundation.layout.Row
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.PlaylistAdd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.TrainListViewModel
import com.example.vottakvot.ViewModel.WorkoutViewModel
import com.example.vottakvot.domain.WorkoutDataItem
import com.example.vottakvot.navigation.navigationLogic.BottomNavigationItem
import com.example.vottakvot.ui.theme.VotTakVotTheme
import com.example.vottakvot.ui.theme.WorkoutCard
import sourceListPopularExample
import sourceListSearchResultExample
import sourceListTrainsForYouExample


// ЭКРАН С РЕЗУЛЬТАТАМИ ПОИСКА


@Composable
fun SearchResultScreen(
    navController: NavHostController,
    title: String,
    trainList: TrainListViewModel
) {
    Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .background(colorScheme.background)
    ) {
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
                    text = title,
                    fontSize = 30.sp,
                    color = colorScheme.onBackground,
                )
                IconCloseButton(
                    modifier = Modifier
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
            // список найденных тренировок

            //val searchedWorkouts =  generalViewModel.workoutListSearchResult.observeAsState(listOf())
/*
            // источник данных для списк тренировок
            val source: List<WorkoutDataItem> = sourceListTrainsForYouExample
            // список тренировок
            val trainList = TrainListViewModel(
                source
            )

 */
            val searchedWorkouts =  trainList.workoutListGeneral.observeAsState(listOf())
                //workoutViewModel.workoutList.observeAsState(listOf())
                // значение по умолчанию - пустая коллекция
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorScheme.background),
            ) {
                items(searchedWorkouts.value)
                    {
                    // this - LazyItemScope
                    // it - workoutItem
                        it ->
                        val workoutItem = it
                        WorkoutCard(
                            //workoutViewModel = workoutViewModel,
                            workoutItem = workoutItem,
                            // слушатели клика
                            onAddedClickListener = {
                                trainList.changeAddedStatusList(it)
                                //generalViewModel.changeAddedStatusListSearchResult(it)
                            },
                            onLikeClickListener = {
                                trainList.changeLikedStatusList(it)
                                //generalViewModel.changeLikedStatusListSearchResult(it)
                            },
                            onPlayClickListener = {
                                trainList.changePlayingStatusList(it)
                                //generalViewModel.changePlayingtatusListSearchResult(it)
                            }
                        )
                    }

            }
        }
    }

/*
repeat(models.value?.size!!) {
    item {

    }
}
*/

@Composable
private fun IconCloseButton(
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
            onItemClickListener(
            )
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

/*
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchResult(
    generalViewModel: GeneralViewModel,
    workoutViewModel: WorkoutViewModel,
    title: String
)
{
    val navHostController = rememberNavController()

    val listItems = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.MyWorkouts,
        BottomNavigationItem.Favourite,
        BottomNavigationItem.Profile,
    )
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = colorScheme.surface,
                )
            {
                val navBackStackEntry by navHostController.currentBackStackEntryAsState()

                listItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        onClick = { selectedItemIndex = index },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        label = {
                            androidx.compose.material3.Text(
                                text = stringResource(id = item.titleResId),
                                modifier = androidx.compose.ui.Modifier
                                    .padding(0.dp)
                                    .align(alignment = Alignment.CenterVertically)
                                    .fillMaxHeight(0.2f),
                                fontSize = 10.sp,
                                maxLines = 1,
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.White,
                            selectedIconColor = Color.Red,//colorScheme.onSurface,//colorScheme.onSecondaryContainer,
                            selectedTextColor = colorScheme.onSurface, //colorScheme.onSurface,
                            unselectedIconColor = colorScheme.onSurface,//colorScheme.onSecondaryContainer, //colorScheme.onSurface,
                            unselectedTextColor = colorScheme.onSurface//colorScheme.onSurface,

                        )
                    )
                }
            }
        }
    )
    {
        ContentSearchResultScreen(
            generalViewModel = generalViewModel,
            workoutViewModel = workoutViewModel,
            title = title
        )
    }
}
*/

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SearchResultScreenWhitePrev() {
    VotTakVotTheme(
        darkTheme = false)
        {
            val context = LocalContext.current
            val navController = NavHostController(context = context)
            val title = stringResource(R.string.search_result)
            val trainList: TrainListViewModel =
                TrainListViewModel(source = sourceListSearchResultExample)
            SearchResultScreen (
                navController = navController,
                title = title,
                trainList = trainList
            )
        }
}


@Preview
@Composable
fun SearchResultScreenBlackPrev() {
    VotTakVotTheme(
        darkTheme = true)
    {
        val context = LocalContext.current
        val navController = NavHostController(context = context)
        val title = stringResource(R.string.search_result)
        val trainList: TrainListViewModel =
            TrainListViewModel(source = sourceListSearchResultExample)
        SearchResultScreen (
            navController = navController,
            title = title,
            trainList = trainList
        )
    }
}

