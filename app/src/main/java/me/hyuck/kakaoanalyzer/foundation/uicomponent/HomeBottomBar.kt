package me.hyuck.kakaoanalyzer.foundation.uicomponent

import androidx.annotation.FloatRange
import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import me.hyuck.kakaoanalyzer.R

enum class HomeSections(
	@StringRes val title: Int,
	val icon: ImageVector,
	val route: String
) {
	CHATS(R.string.tab_chats, Icons.Outlined.Home, "home/chats"),
	GUIDE(R.string.tab_chats, Icons.Outlined.QuestionMark, "home/guide"),
	SETTINGS(R.string.tab_chats, Icons.Outlined.Settings, "home/settings")
}

@Composable
fun KakaoAnalyzerBottomBar(
	tabs: Array<HomeSections>,
	currentRoute: String,
	navigateToRoute: (String) -> Unit,
	color: Color = MaterialTheme.colorScheme.secondary,
	contentColor: Color = MaterialTheme.colorScheme.primary
) {

}

@Composable
private fun KakaoAnalyzerBottomNavItemLayout(
	icon: @Composable BoxScope.() -> Unit,
	text: @Composable BoxScope.() -> Unit,
	@FloatRange(from = 0.0, to = 1.0) animationProgress: Float
) {
	Layout(
		content = {

		}
	) {measurables, constraints ->
		val iconPlaceable = measurables.first { it.layoutId == "icon" }.measure(constraints)
		val textPlaceable = measurables.first { it.layoutId == "text" }.measure(constraints)

		placeTextAndIcon(
			textPlaceable,
			iconPlaceable,
			constraints.maxWidth,
			constraints.maxHeight,
			animationProgress
		)
	}
}

private fun MeasureScope.placeTextAndIcon(
	textPlaceable: Placeable,
	iconPlaceable: Placeable,
	width: Int,
	height: Int,
	@FloatRange(from = 0.0, to = 1.0) animationProgress: Float
): MeasureResult {
	val iconY = (height - iconPlaceable.height) / 2
	val textY = (height - textPlaceable.height) / 2

	val textWidth = textPlaceable.width * animationProgress
	val iconX = (width - textWidth - iconPlaceable.width) / 2
	val textX = iconX + iconPlaceable.width

	return layout(width, height) {
		iconPlaceable.placeRelative(iconX.toInt(), iconY)
		if (animationProgress != 0f) {
			textPlaceable.placeRelative(textX.toInt(), textY)
		}
	}
}

@Composable
private fun KakaoAnalyzerBottomNavIndicator(
	strokeWidth: Dp = 2.dp,
	color: Color,
	shape: Shape = BottomNavIndicatorShape
) {
	Spacer(
		modifier = Modifier
			.fillMaxSize()
			.then(BottomNavigationItemPadding)
			.border(strokeWidth, color, shape)
	)
}

private val BottomNavIndicatorShape = RoundedCornerShape(percent = 50)
private val BottomNavigationItemPadding = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)


//@Preview
//@Composable
//private fun KakaoAnalyzerBottomNavItemLayoutPreview() {
//	KakaoAnalyzerComposeTheme {
//		KakaoAnalyzerBottomNavItemLayout(
//
//		)
//	}
//}