package com.example.vottakvot.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vottakvot.R
import com.example.vottakvot.navigation.BottomNavigationItem
import com.example.vottakvot.ui.theme.VotTakVotTheme

// домашний экран

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenWithoutOnboarding() {
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
                            androidx.compose.material.Icon(item.icon, contentDescription = null)
                        },
                        label = {
                            Text(
                            text = stringResource(id = item.titleResId),
                            modifier = Modifier
                                .padding(0.dp)
                                .align(alignment = Alignment.CenterVertically)
                                .fillMaxHeight(0.2f),
                            fontSize = 10.sp,
                                maxLines = 1,
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            selectedTextColor = MaterialTheme.colorScheme.onSurface,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                            indicatorColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        }
    )
    {
        Text(text = "some text", modifier = Modifier.padding(it))
    }
}

@Preview
@Composable
fun HomeScreenWithoutOnboardingWhitePrev() {
    VotTakVotTheme(
        darkTheme = false)
    {
        HomeScreenWithoutOnboarding()
    }
}

@Preview
@Composable
fun HomeScreenWithoutOnboardingBlackPrev() {
    VotTakVotTheme(
        darkTheme = true)
    {
        HomeScreenWithoutOnboarding()
    }
}