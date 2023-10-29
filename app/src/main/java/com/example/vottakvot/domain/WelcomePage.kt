package com.example.vottakvot.domain

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class WelcomePage(
    //@DrawableRes
    val image: Int,
    val title: String,
    val description: String,
    val colorText: Color = Color.Black
)
