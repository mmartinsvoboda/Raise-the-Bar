package com.mmartinsvoboda.sporttrackingapp.presentation.ui.attr

import androidx.compose.material.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mmartinsvoboda.sporttrackingapp.R

val appFontFamily = FontFamily(
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

@OptIn(ExperimentalTextApi::class)
val Typography = Typography(
    defaultFontFamily = appFontFamily,
    h1 = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 96.sp,
        letterSpacing = (-1.5).sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 60.sp,
        letterSpacing = (-0.5).sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    h4 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 34.sp,
        letterSpacing = 0.25.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        letterSpacing = 0.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        letterSpacing = 0.15.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    subtitle1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    subtitle2 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.5.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    button = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 1.25.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    caption = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.4.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    overline = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = 1.5.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    )
)