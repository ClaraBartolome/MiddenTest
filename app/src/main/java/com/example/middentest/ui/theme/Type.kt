package com.example.middentest.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.middentest.R


val sfDisplayFontFamily = FontFamily(
    Font(R.font.sfprodisplay_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.sfprodisplay_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.sfprodisplay_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.sfprodisplay_heavy_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.sfprodisplay_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.sfprodisplay_black_italic, FontWeight.Black, FontStyle.Italic),
    Font(R.font.sfprodisplay_semibold_italic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.sfprodisplay_thin_italic, FontWeight.Thin, FontStyle.Italic),
    Font(R.font.sfprodisplay_ultralight_italic, FontWeight.ExtraLight, FontStyle.Italic),
)

val oswaldFontFamily = FontFamily(
    Font(R.font.oswald_medium, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.oswald_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.oswald_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.oswald_regular_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.oswald_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.oswald_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.oswald_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.oswald_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.oswald_heavy, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.oswald_heavy_italic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.oswald_semibold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.oswald_semi_bold_italic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.oswald_extralight, FontWeight.ExtraLight, FontStyle.Normal),
    Font(R.font.oswald_extralight_italic, FontWeight.ExtraLight, FontStyle.Italic),
)

val openSansFontFamily = FontFamily(
    Font(R.font.opensans_light, FontWeight.Light, FontStyle.Normal),
    Font(R.font.opensans_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.opensans_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.opensans_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.opensans_semibold_italic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.opensans_semibold, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.opensans_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.opensans_extrabold, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.opensans_extrabold_italic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.opensans_regular, FontWeight.Normal, FontStyle.Normal),
)

private val defaultTypography = Typography()

val CustomTypography = Typography(
    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = sfDisplayFontFamily),
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = sfDisplayFontFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = sfDisplayFontFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = sfDisplayFontFamily),
    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = sfDisplayFontFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = sfDisplayFontFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = sfDisplayFontFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = sfDisplayFontFamily),
    titleLarge = defaultTypography.titleLarge.copy(fontFamily = sfDisplayFontFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = sfDisplayFontFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = sfDisplayFontFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = sfDisplayFontFamily),
    labelLarge = defaultTypography.labelLarge.copy(fontFamily = sfDisplayFontFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = sfDisplayFontFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = sfDisplayFontFamily),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)