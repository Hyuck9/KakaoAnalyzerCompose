package me.hyuck.kakaoanalyzer.foundation.uicomponent

import androidx.compose.animation.animateColorAsState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import me.hyuck.kakaoanalyzer.foundation.theme.KakaoAnalyzerTheme
import me.hyuck.kakaoanalyzer.model.Filter

@Composable
fun FilterBar() {

}

@Composable
fun FilterChip(
	filter: Filter,
	modifier: Modifier = Modifier,
	shape: Shape = MaterialTheme.shapes.small
) {
	val (selected, setSelected) = filter.enabled
	val backgroundColor by animateColorAsState(
		if (selected) KakaoAnalyzerTheme.colors.brandSecondary else KakaoAnalyzerTheme.colors.uiBackground
	)
	val border = Modifier.fadeInDiagonalGradientBorder(
		showBorder = !selected,
		colors = KakaoAnalyzerTheme.colors.interactiveSecondary,
		shape = shape
	)
	val textColor by animateColorAsState(
		if (selected) Color.Black else KakaoAnalyzerTheme.colors.textSecondary
	)
}

