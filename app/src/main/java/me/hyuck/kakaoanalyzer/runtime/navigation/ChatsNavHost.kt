package me.hyuck.kakaoanalyzer.runtime.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import me.hyuck.kakaoanalyzer.features.home.chats.ui.ChatsScreen
import me.hyuck.kakaoanalyzer.features.home.guide.ui.GuideScreen
import me.hyuck.kakaoanalyzer.features.home.settings.ui.SettingsScreen
import me.hyuck.kakaoanalyzer.foundation.uicomponent.HomeSections
import me.hyuck.kakaoanalyzer.runtime.MainDestinations

fun NavGraphBuilder.addHomeGraph() {
	navigation(
		route = MainDestinations.HOME_ROUTE,
		startDestination = HomeSections.CHATS.route
	) {
		composable(HomeSections.CHATS.route) {
			ChatsScreen()
		}
		composable(HomeSections.GUIDE.route) {
			GuideScreen()
		}
		composable(HomeSections.SETTINGS.route) {
			SettingsScreen()
		}
	}
}