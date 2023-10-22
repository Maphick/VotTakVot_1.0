@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.vottakvot.screen

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.os.Build
import android.util.Log
import android.view.RoundedCorner
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vottakvot.navigation.BottomNavigationItem
import com.example.vottakvot.ui.theme.VotTakVotTheme
import java.lang.reflect.Modifier

// домашний экран после прохождения онбординга
// домашний экран

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenWithOnboarding() {
    val navHostController = rememberNavController()

    val listItems = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.MyWorkouts,
        BottomNavigationItem.Favourite,
        BottomNavigationItem.Profile
    )
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    Scaffold (
        bottomBar = {
            NavigationBar {
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
                            selectedIconColor = colorScheme.onSecondaryContainer,
                            selectedTextColor = colorScheme.onSurface,
                            unselectedIconColor = colorScheme.onSurfaceVariant,
                            unselectedTextColor = colorScheme.onSurface,
                            indicatorColor = colorScheme.primary
                        )
                    )
                }
            }
        }
    )
    {
        androidx.compose.material3.Text(
            text = "some text",
            modifier = androidx.compose.ui.Modifier.padding(it)
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
        HomeScreenWithOnboarding()
    }
}


@Preview
@Composable
fun HomeScreenWithOnboardingBlackPrev() {
    VotTakVotTheme(
        darkTheme = true)
    {
        HomeScreenWithOnboarding()
    }
}
