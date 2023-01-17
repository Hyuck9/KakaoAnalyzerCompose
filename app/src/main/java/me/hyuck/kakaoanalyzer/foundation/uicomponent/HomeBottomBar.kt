package me.hyuck.kakaoanalyzer.foundation.uicomponent

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.QuestionMark
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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