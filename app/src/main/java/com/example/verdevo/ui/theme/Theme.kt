package com.example.verdevo.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColors = lightColorScheme(
    primary = White,
    onPrimary = Color.White,
    secondary = Gray,
    onSecondary = Color.Gray
)

private val DarkColors = darkColorScheme(
    primary = Green,
    onPrimary = Color.Green,
    secondary = Black,
    onSecondary = Color.Black
)

@Composable
fun VerdevoTheme(
    darkTheme : Boolean = false,
    content : @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColors
    }
    else {
        LightColors
    }
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}