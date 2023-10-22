package com.example.vottakvot.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.vottakvot.R

sealed class BottomNavigationItem (
    val titleResId: Int,
    val icon: ImageVector
)
{
    object Home: BottomNavigationItem(
        titleResId = R.string.botton_navigation_item_main,
        icon = Icons.Outlined.Home
    )
    object MyWorkouts: BottomNavigationItem(
        titleResId = R.string.botton_navigation_item_my_workouts,
        icon = Icons.Outlined.Edit
    )
    object Favourite: BottomNavigationItem(
        titleResId = R.string.botton_navigation_item_favourite,
        icon = Icons.Outlined.Favorite
    )
    object Profile: BottomNavigationItem(
        titleResId = R.string.botton_navigation_item_profile,
        icon = Icons.Outlined.Person
    )



}