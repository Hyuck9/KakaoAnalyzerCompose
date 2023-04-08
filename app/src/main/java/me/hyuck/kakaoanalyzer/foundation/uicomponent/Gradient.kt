package me.hyuck.kakaoanalyzer.foundation.uicomponent

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.diagonalGradientBorder(
	colors: List<Color>,
	borderSize: Dp = 2.dp,
	shape: Shape
) = border(
	width = borderSize,
	brush = Brush.linearGradient(colors),
	shape = shape
)

fun Modifier.fadeInDiagonalGradientBorder(
	showBorder: Boolean,
	colors: List<Color>,
	borderSize: Dp = 2.dp,
	shape: Shape
) = composed {
	val animatedColors = List(colors.size) { i ->
		animateColorAsState(if (showBorder) colors[i] else colors[i].copy(alpha = 0f)).value
	}
	diagonalGradientBorder(
		colors = animatedColors,
		borderSize = borderSize,
		shape = shape
	)
}