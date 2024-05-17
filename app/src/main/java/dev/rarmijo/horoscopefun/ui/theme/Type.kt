package dev.rarmijo.horoscopefun.ui.theme

import android.os.Build
import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import dev.rarmijo.horoscopefun.R


val bodyFontFamily = FontFamily.Default
    //FontFamily(Font(R.font.lora_variable))


val displayFontFamily = FontFamily(Font(R.font.dancing_script_variable))

//val displayFontFamily = FontFamily(
//    Font(R.font.dancing_script_regular),
//    Font(R.font.dancing_script_semibold),
//    Font(R.font.dancing_script_medium),
//    Font(R.font.dancing_script_bold)
//)
//

// Default Material 3 typography values
val baseline = Typography()

val AppTypography =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    Typography(
        displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
        displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
        displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
        headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
        headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
        headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),
        titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
        titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
        titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
        bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
        bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
        bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
        labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
        labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
        labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
    )
    } else {
        baseline
    }


