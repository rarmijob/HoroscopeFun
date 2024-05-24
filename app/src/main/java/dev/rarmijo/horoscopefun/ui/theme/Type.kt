package dev.rarmijo.horoscopefun.ui.theme

import android.os.Build
import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import dev.rarmijo.horoscopefun.R


val bodyFontFamily = FontFamily(Font(R.font.dancing_script_variable))

//FontFamily.Default
//FontFamily(Font(R.font.lora_variable))


val displayFontFamily = FontFamily(Font(R.font.dancing_script_variable))


// Default Material 3 typography values
val baseline = Typography()

val AppTypography =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Typography(
            displayLarge = baseline.displayLarge.copy(
                fontFamily = displayFontFamily,
                color = OrangeMystic
            ),
            displayMedium = baseline.displayMedium.copy(
                fontFamily = displayFontFamily,
                color = OrangeMystic
            ),
            displaySmall = baseline.displaySmall.copy(
                fontFamily = displayFontFamily,
                color = OrangeMystic
            ),
            headlineLarge = baseline.headlineLarge.copy(
                fontFamily = displayFontFamily,
                color = OrangeMystic
            ),
            headlineMedium = baseline.headlineMedium.copy(
                fontFamily = displayFontFamily,
                color = OrangeMystic
            ),
            headlineSmall = baseline.headlineSmall.copy(
                fontFamily = displayFontFamily,
                color = OrangeMystic
            ),
            titleLarge = baseline.titleLarge.copy(
                fontFamily = displayFontFamily,
                color = OrangeMystic
            ),
            titleMedium = baseline.titleMedium.copy(
                fontFamily = displayFontFamily,
                color = OrangeMystic
            ),
            titleSmall = baseline.titleSmall.copy(
                fontFamily = displayFontFamily,
                color = OrangeMystic
            ),
            bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily, color = OrangeMystic),
            bodyMedium = baseline.bodyMedium.copy(
                fontFamily = bodyFontFamily,
                color = OrangeMystic
            ),
            bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily, color = OrangeMystic),
            labelLarge = baseline.labelLarge.copy(),
            labelMedium = baseline.labelMedium.copy(),
            labelSmall = baseline.labelSmall.copy(),
        )
    } else {
        baseline
    }


