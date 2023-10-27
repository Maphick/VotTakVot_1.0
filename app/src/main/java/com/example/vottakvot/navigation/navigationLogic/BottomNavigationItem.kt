package com.example.vottakvot.navigation.navigationLogic

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddHome
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.vottakvot.R

sealed class BottomNavigationItem (
    val screen: Screen,
    val titleResId: Int,
    val icon: ImageVector
)
{
    object Home: BottomNavigationItem(
        screen = Screen.Home,
        titleResId = R.string.botton_navigation_item_main,
        icon = Icons.Default.Home
    )
    object MyWorkouts: BottomNavigationItem(
        screen = Screen.MyTrains,
        titleResId = R.string.botton_navigation_item_my_workouts,
        icon = Icons.Outlined.EditNote
    )
    object Favourite: BottomNavigationItem(
        screen = Screen.Favourite,
        titleResId = R.string.botton_navigation_item_favourite,
        icon = Icons.Outlined.FavoriteBorder
    )
    object Profile: BottomNavigationItem(
        screen = Screen.Profile,
        titleResId = R.string.botton_navigation_item_profile,
        icon = Icons.Outlined.Person
    )



}