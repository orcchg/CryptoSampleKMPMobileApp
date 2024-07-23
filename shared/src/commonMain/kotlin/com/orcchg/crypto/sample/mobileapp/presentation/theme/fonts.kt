package com.orcchg.crypto.sample.mobileapp.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cryptosamplekmpmobileapp.shared.generated.resources.Res
import cryptosamplekmpmobileapp.shared.generated.resources.montserrat_black
import cryptosamplekmpmobileapp.shared.generated.resources.montserrat_bold
import cryptosamplekmpmobileapp.shared.generated.resources.montserrat_extrabold
import cryptosamplekmpmobileapp.shared.generated.resources.montserrat_extralight
import cryptosamplekmpmobileapp.shared.generated.resources.montserrat_light
import cryptosamplekmpmobileapp.shared.generated.resources.montserrat_medium
import cryptosamplekmpmobileapp.shared.generated.resources.montserrat_regular
import cryptosamplekmpmobileapp.shared.generated.resources.montserrat_semibold
import cryptosamplekmpmobileapp.shared.generated.resources.montserrat_thin
import org.jetbrains.compose.resources.Font

@Composable
fun MontserratFontFamily() = FontFamily(
    Font(Res.font.montserrat_thin, weight = FontWeight.Thin),
    Font(Res.font.montserrat_extralight, weight = FontWeight.ExtraLight),
    Font(Res.font.montserrat_light, weight = FontWeight.Light),
    Font(Res.font.montserrat_regular, weight = FontWeight.Normal),
    Font(Res.font.montserrat_medium, weight = FontWeight.Medium),
    Font(Res.font.montserrat_semibold, weight = FontWeight.SemiBold),
    Font(Res.font.montserrat_bold, weight = FontWeight.Bold),
    Font(Res.font.montserrat_extrabold, weight = FontWeight.ExtraBold),
    Font(Res.font.montserrat_black, weight = FontWeight.Black)
)

@Composable
fun AppTypography() =
    Typography(
        displayLarge =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Light,
            fontSize = 96.sp,
            letterSpacing = (-1.5).sp,
        ),
        displayMedium =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Light,
            fontSize = 60.sp,
            letterSpacing = (-0.5).sp,
        ),
        displaySmall =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 48.sp,
            letterSpacing = 0.sp,
        ),
        headlineLarge =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 40.sp,
            letterSpacing = 0.25.sp,
        ),
        headlineMedium =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 34.sp,
            letterSpacing = 0.25.sp,
        ),
        headlineSmall =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            letterSpacing = 0.sp,
        ),
        titleLarge =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            letterSpacing = 0.15.sp,
        ),
        titleMedium =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            letterSpacing = 0.15.sp,
        ),
        titleSmall =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 0.1.sp,
        ),
        bodyLarge =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            letterSpacing = 0.5.sp,
        ),
        bodyMedium =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            letterSpacing = 0.25.sp,
        ),
        bodySmall =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            letterSpacing = 0.4.sp,
        ),
        labelLarge =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 1.25.sp,
        ),
        labelMedium =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            letterSpacing = 1.5.sp,
        ),
        labelSmall =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            letterSpacing = 1.5.sp,
        ),
    )
