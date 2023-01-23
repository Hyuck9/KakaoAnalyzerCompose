package me.hyuck.kakaoanalyzer.foundation.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
	primary = Shadow1,
	secondary = Ocean2,
	tertiary = Pink80,
	background = Neutral8
)

private val LightColorScheme = lightColorScheme(
	primary = Shadow5,
	secondary = Ocean3,
	tertiary = Pink40,
	background = Neutral0

	/* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private val LightColorPalette = KakaoAnalyzerColors(
	brand = Shadow5,
	brandSecondary = Ocean3,
	uiBackground = Neutral0,
	uiBorder = Neutral4,
	uiFloated = FunctionalGrey,
	textSecondary = Neutral7,
	textHelp = Neutral6,
	textInteractive = Neutral0,
	textLink = Ocean11,
	iconSecondary = Neutral7,
	iconInteractive = Neutral0,
	iconInteractiveInactive = Neutral1,
	error = FunctionalRed,
	gradient6_1 = listOf(Shadow4, Ocean3, Shadow2, Ocean3, Shadow4),
	gradient6_2 = listOf(Rose4, Lavender3, Rose2, Lavender3, Rose4),
	gradient3_1 = listOf(Shadow2, Ocean3, Shadow4),
	gradient3_2 = listOf(Rose2, Lavender3, Rose4),
	gradient2_1 = listOf(Shadow4, Shadow11),
	gradient2_2 = listOf(Ocean3, Shadow3),
	gradient2_3 = listOf(Lavender3, Rose2),
	tornado1 = listOf(Shadow4, Ocean3),
	isDark = false
)

private val DarkColorPalette = KakaoAnalyzerColors(
	brand = Shadow1,
	brandSecondary = Ocean2,
	uiBackground = Neutral8,
	uiBorder = Neutral3,
	uiFloated = FunctionalDarkGrey,
	textPrimary = Shadow1,
	textSecondary = Neutral0,
	textHelp = Neutral1,
	textInteractive = Neutral7,
	textLink = Ocean2,
	iconPrimary = Shadow1,
	iconSecondary = Neutral0,
	iconInteractive = Neutral7,
	iconInteractiveInactive = Neutral6,
	error = FunctionalRedDark,
	gradient6_1 = listOf(Shadow5, Ocean7, Shadow9, Ocean7, Shadow5),
	gradient6_2 = listOf(Rose11, Lavender7, Rose8, Lavender7, Rose11),
	gradient3_1 = listOf(Shadow9, Ocean7, Shadow5),
	gradient3_2 = listOf(Rose8, Lavender7, Rose11),
	gradient2_1 = listOf(Ocean3, Shadow3),
	gradient2_2 = listOf(Ocean4, Shadow2),
	gradient2_3 = listOf(Lavender3, Rose3),
	tornado1 = listOf(Shadow4, Ocean3),
	isDark = true
)

@Composable
fun KakaoAnalyzerTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	// Dynamic color is available on Android 12+
	dynamicColor: Boolean = false,
	content: @Composable () -> Unit
) {
	val colors = if (darkTheme) DarkColorPalette else LightColorPalette
	val colorScheme = when {
		dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
			val context = LocalContext.current
			if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
		}
		darkTheme -> DarkColorScheme
		else -> LightColorScheme
	}
	val view = LocalView.current
	if (!view.isInEditMode) {
		SideEffect {
			val window = (view.context as Activity).window
			window.statusBarColor = colorScheme.primary.toArgb()
			(view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
			WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
		}
	}

	ProvideKakaoAnalyzeColors(colors) {
		MaterialTheme(
			colorScheme = colorScheme,
			typography = Typography,
			shapes = Shapes,
			content = content
		)
	}
}

object KakaoAnalyzerTheme {
	val colors: KakaoAnalyzerColors
		@Composable get() = LocalKakaoAnalyzerColors.current
}

@Composable
fun ProvideKakaoAnalyzeColors(
	colors: KakaoAnalyzerColors,
	content: @Composable () -> Unit
) {
	val colorPalette = remember {
		colors.copy()
	}
	colorPalette.update(colors)
	CompositionLocalProvider(LocalKakaoAnalyzerColors provides colorPalette, content = content)
}

private val LocalKakaoAnalyzerColors = staticCompositionLocalOf<KakaoAnalyzerColors> {
	error("No KakaoAnalyzerColorPalette provided")
}