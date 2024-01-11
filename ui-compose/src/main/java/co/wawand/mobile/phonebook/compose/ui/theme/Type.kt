package co.wawand.mobile.phonebook.compose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import co.wawand.mobile.phonebook.base.R

private val defaultTypography = Typography()
private val ralewayFontFamily = FontFamily(
    Font(R.font.raleway_light, FontWeight.Light),
    Font(R.font.raleway_regular, FontWeight.Normal),
    Font(R.font.raleway_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.raleway_medium, FontWeight.Medium),
    Font(R.font.raleway_bold, FontWeight.Bold)
)


// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = ralewayFontFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = ralewayFontFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = ralewayFontFamily),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = ralewayFontFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = ralewayFontFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = ralewayFontFamily),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = ralewayFontFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = ralewayFontFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = ralewayFontFamily),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = ralewayFontFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = ralewayFontFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = ralewayFontFamily),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = ralewayFontFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = ralewayFontFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = ralewayFontFamily)
)
