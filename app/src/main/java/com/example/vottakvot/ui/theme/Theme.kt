package com.example.vottakvot.ui.theme

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat



private val DarkColorScheme = darkColorScheme(
    primary = primary_dark,
    secondary = secondary_dark,
    tertiary = tertiary_dark,
    background = background_dark,
    surface = surface_dark,
    surfaceVariant = surface_variant_dark,
    secondaryContainer = secondary_container_dark,
    onPrimary = Color.Black,
    onSecondary = Color.Red,
    onSecondaryContainer = on_secondary_container_dark,
    onTertiary = Color.White,
    onBackground = on_background_dark,
    onSurface = on_surface_dark,
    onSurfaceVariant = on_surface_variant_dark
)

private val LightColorScheme = lightColorScheme(
    primary = primary_light ,
    secondary = secondary_light,
    tertiary = tertiary_light,
    background =  background_light,
    surface = surface_light,
    surfaceVariant = surface_variant_light,
    secondaryContainer = secondary_container_light,
    onPrimary = Color.White,
    onSecondary = Color.Red,
    onSecondaryContainer = on_secondary_container_light,
    onTertiary = Color.Black,
    onBackground = on_background_light,
    onSurface = on_surface_light,
    onSurfaceVariant = on_surface_variant_light
)
@SuppressLint("SuspiciousIndentation")
@Composable
fun VotTakVotTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    //dynamicColor: Boolean = true,
    content: @Composable () -> Unit) {
 val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
     /*
     when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme ->
            DarkColorScheme
        else ->
            LightColorScheme
    }
    */

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
