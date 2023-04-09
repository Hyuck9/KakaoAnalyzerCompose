package me.hyuck.kakaoanalyzer.foundation.uicomponent

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.hyuck.kakaoanalyzer.foundation.theme.KakaoAnalyzerTheme
import me.hyuck.kakaoanalyzer.model.Filter

@Composable
fun FilterBar(
	filters: List<Filter>
) {
	LazyRow(
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.spacedBy(8.dp),
		contentPadding = PaddingValues(start = 12.dp, end = 8.dp),
		modifier = Modifier.heightIn(min = 56.dp)
	) {
		items(filters) { filter ->
			FilterChip(filter = filter, shape = MaterialTheme.shapes.large)
		}
	}
}

@Composable
fun FilterChip(
	filter: Filter,
	modifier: Modifier = Modifier,
	shape: Shape = MaterialTheme.shapes.large
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

	KakaoAnalyzerSurface(
		modifier = modifier.height(28.dp),
		color = backgroundColor,
		contentColor = textColor,
		shape = shape,
		elevation = 2.dp
	) {
		val interactionSource = remember { MutableInteractionSource() }

		val pressed by interactionSource.collectIsPressedAsState()
		val backgroundPressed = if (pressed) {
			Modifier.offsetGradientBackground(
				KakaoAnalyzerTheme.colors.interactiveSecondary,
				200f,
				0f
			)
		} else {
			Modifier.background(Color.Transparent)
		}
		Box(
			modifier = Modifier
				.toggleable(
					value = selected,
					onValueChange = setSelected,
					interactionSource = interactionSource,
					indication = null
				)
				.then(backgroundPressed)
				.then(border)
		) {
			Text(
				text = filter.name,
				style = MaterialTheme.typography.labelMedium,
				maxLines = 1,
				modifier = Modifier.padding(
					horizontal = 20.dp,
					vertical = 6.dp
				)
			)
		}

	}
}




@Preview
@Composable
private fun FilterDisabledPreview() {
	KakaoAnalyzerTheme {
		FilterChip(Filter(name = "Demo", enabled = false), Modifier.padding(4.dp))
	}
}

@Preview
@Composable
private fun FilterEnabledPreview() {
	KakaoAnalyzerTheme {
		FilterChip(Filter(name = "Demo", enabled = true), Modifier.padding(4.dp))
	}
}