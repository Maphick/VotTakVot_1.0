@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.vottakvot.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.WorkoutViewModel
import com.example.vottakvot.domain.WorkoutDataItem
import com.example.vottakvot.navigation.BottomNavigationItem
import com.example.vottakvot.ui.theme.VotTakVotTheme
import com.example.vottakvot.ui.theme.WorkoutCard

// домашний экран после прохождения онбординга
// домашний экран

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenWithOnboarding(
    workoutViewModel: WorkoutViewModel
) {
    NavigationHomeScreen(
        workoutViewModel = workoutViewModel
    )
}


@Composable
fun ContentHomeScreen(
    workoutViewModel: WorkoutViewModel
) {
    Box(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            LazyColumn {
                item {
                    Text(text = "Title", color = Color.White)
                }
                items(10) {
                    val workoutItem = WorkoutDataItem()
                   // WorkoutCard(
                  //      workoutItem = workoutItem
                  //  )
                }
                item {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null
                    )
                }
                items(500) {
                    val workoutItem = WorkoutDataItem()
                   // WorkoutCard(
                    //    workoutViewModel = workoutViewModel,
                    //    workoutItem = workoutItem
                   // )
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationHomeScreen(
    workoutViewModel: WorkoutViewModel
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
                            indicatorColor = colorScheme.tertiary,
                            selectedIconColor = colorScheme.onSecondaryContainer,
                            selectedTextColor = colorScheme.onSurface, //colorScheme.onSurface,
                            unselectedIconColor = Color.White,//colorScheme.onSecondaryContainer, //colorScheme.onSurface,
                            unselectedTextColor = colorScheme.onSurface//colorScheme.onSurface,

                        )
                    )
                }
            }
        }
    )
    {

        ContentHomeScreen(workoutViewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreenWithOnboardingWhitePrev() {
    VotTakVotTheme(
        darkTheme = false)
    {
        val workoutViewModel = WorkoutViewModel()
        HomeScreenWithOnboarding(
            workoutViewModel
        )
    }
}


@Preview
@Composable
fun HomeScreenWithOnboardingBlackPrev() {
    VotTakVotTheme(
        darkTheme = true)
    {
        val workoutViewModel = WorkoutViewModel()
        HomeScreenWithOnboarding(
            workoutViewModel
        )
    }
}

