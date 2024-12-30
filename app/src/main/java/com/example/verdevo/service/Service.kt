package com.example.verdevo.service

import androidx.compose.ui.graphics.Color
import com.example.verdevo.ui.theme.DarkGreen
import com.example.verdevo.ui.theme.Gray
import com.example.verdevo.ui.theme.Green

fun calculateOverlayColor(temperature : Float): Color {
    return when {
        temperature <= 22 -> Green
        temperature >= 22  -> DarkGreen
        else -> Gray
    }
}
 fun calculateEnergyEfficience(luminance : Float, area : Float) : Float {
     return ((luminance * 0.5f) * area * 15f * 3600f) / 1500000f /* Considerando 15 W como potência padrão em 1 hora*/
 }