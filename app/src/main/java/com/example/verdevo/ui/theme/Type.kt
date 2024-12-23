package com.example.verdevo.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.verdevo.R

val livvicFontFamily = FontFamily(
    Font(R.font.livvic_bold, FontWeight.Bold),
    Font(R.font.livvic_regular, FontWeight.Normal),
    Font(R.font.livvic_black, FontWeight.Black),
    Font(R.font.livvic_medium, FontWeight.Medium)
)

private const val activatePreview = true

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = if (activatePreview) FontFamily.Default else livvicFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = if (activatePreview) FontFamily.Default else livvicFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = if (activatePreview) FontFamily.Default else livvicFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = if (activatePreview) FontFamily.Default else livvicFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = if (activatePreview) FontFamily.Default else livvicFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = if (activatePreview) FontFamily.Default else livvicFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = if (activatePreview) FontFamily.Default else livvicFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    labelLarge = TextStyle(
        fontFamily = if (activatePreview) FontFamily.Default else livvicFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = if (activatePreview) FontFamily.Default else livvicFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
)