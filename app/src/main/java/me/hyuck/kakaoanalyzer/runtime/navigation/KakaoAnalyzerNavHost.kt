package me.hyuck.kakaoanalyzer.runtime.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import me.hyuck.kakaoanalyzer.features.home.chats.ui.ChatsScreen
import me.hyuck.kakaoanalyzer.features.home.chats.ui.ChatsViewModel
import me.hyuck.kakaoanalyzer.features.home.guide.ui.GuideScreen
import me.hyuck.kakaoanalyzer.features.home.settings.ui.SettingsScreen
import me.hyuck.kakaoanalyzer.features.statistics.common.ui.StatisticsScreen
import me.hyuck.kakaoanalyzer.features.statistics.common.ui.StatisticsViewModel
import me.hyuck.kakaoanalyzer.foundation.uicomponent.HomeSections
import me.hyuck.kakaoanalyzer.runtime.MainDestinations

fun NavGraphBuilder.kakaoAnalyzerNavHost(
    onChatSelected: (String, NavBackStackEntry) -> Unit,
    upPress: () -> Unit
) {
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.CHATS.route
    ) {
        addHomeGraph(onChatSelected)
    }
    composable(
        route = "${MainDestinations.CHAT_DETAIL_ROUTE}/{${MainDestinations.CHAT_ID_KEY}}",
        arguments = listOf(navArgument(MainDestinations.CHAT_ID_KEY) { type = NavType.StringType })
    ) { navBackStackEntry ->
        val arguments = requireNotNull(navBackStackEntry.arguments)
        arguments.getString(MainDestinations.CHAT_ID_KEY)
        ?.let {
            val viewModel = hiltViewModel<StatisticsViewModel>()
            StatisticsScreen(viewModel, upPress)
        }
    }
}

fun NavGraphBuilder.addHomeGraph(
    onChatSelected: (String, NavBackStackEntry) -> Unit,
) {
    composable(HomeSections.CHATS.route) { from ->
        val viewModel = hiltViewModel<ChatsViewModel>()
        ChatsScreen(
            viewModel = viewModel,
            onChatClick = { id -> onChatSelected(id, from) }
        )
    }
    composable(HomeSections.GUIDE.route) {
        GuideScreen()
    }
    composable(HomeSections.SETTINGS.route) {
        SettingsScreen()
    }
}