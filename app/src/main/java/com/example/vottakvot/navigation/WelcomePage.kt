package com.example.vottakvot.navigation

import androidx.annotation.DrawableRes
import com.example.vottakvot.R

data class WelcomePage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
)
