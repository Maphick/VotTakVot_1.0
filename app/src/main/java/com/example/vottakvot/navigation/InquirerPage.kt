package com.example.vottakvot.navigation

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.vottakvot.R

data class InquirerPage(
    val title: String,
    val question: String,
    val answers: List<String>,
)
