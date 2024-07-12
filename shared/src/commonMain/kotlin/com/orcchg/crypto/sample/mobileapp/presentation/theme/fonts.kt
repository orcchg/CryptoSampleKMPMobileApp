package com.orcchg.crypto.sample.mobileapp.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cryptosamplekmpmobileapp.shared.generated.resources.Res
import cryptosamplekmpmobileapp.shared.generated.resources.montserrat_regular
import org.jetbrains.compose.resources.Font

@Composable
fun MontserratFontFamily() = FontFamily(
    Font(Res.font.montserrat_regular, weight = FontWeight.Normal),
)

@Composable
fun AppTypography() =
    Typography(
        h1 =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Light,
            fontSize = 96.sp,
            letterSpacing = (-1.5).sp,
        ),
        h2 =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Light,
            fontSize = 60.sp,
            letterSpacing = (-0.5).sp,
        ),
        h3 =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 48.sp,
            letterSpacing = 0.sp,
        ),
        h4 =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 34.sp,
            letterSpacing = 0.25.sp,
        ),
        h5 =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            letterSpacing = 0.sp,
        ),
        h6 =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            letterSpacing = 0.15.sp,
        ),
        subtitle1 =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            letterSpacing = 0.15.sp,
        ),
        subtitle2 =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 0.1.sp,
        ),
        body1 =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            letterSpacing = 0.5.sp,
        ),
        body2 =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            letterSpacing = 0.25.sp,
        ),
        button =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 1.25.sp,
        ),
        caption =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            letterSpacing = 0.4.sp,
        ),
        overline =
        TextStyle(
            fontFamily = MontserratFontFamily(),
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            letterSpacing = 1.5.sp,
        ),
    )
