package me.hyuck.kakaoanalyzer.runtime.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import me.hyuck.kakaoanalyzer.features.detail.keyword.ui.DetailKeywordScreen
import me.hyuck.kakaoanalyzer.features.detail.ui.DetailScreen
import me.hyuck.kakaoanalyzer.features.home.chats.ui.ChatsScreen
import me.hyuck.kakaoanalyzer.features.home.chats.ui.ChatsViewModel
import me.hyuck.kakaoanalyzer.features.home.guide.ui.GuideScreen
import me.hyuck.kakaoanalyzer.features.home.settings.ui.SettingsScreen
import me.hyuck.kakaoanalyzer.features.statistics.common.ui.StatisticsScreen
import me.hyuck.kakaoanalyzer.foundation.uicomponent.HomeSections
import me.hyuck.kakaoanalyzer.model.DetailType
import me.hyuck.kakaoanalyzer.runtime.MainDestinations.CHAT_ID_KEY
import me.hyuck.kakaoanalyzer.runtime.MainDestinations.HOME_ROUTE
import me.hyuck.kakaoanalyzer.runtime.MainDestinations.KEYWORDS_DETAIL_ROUTE
import me.hyuck.kakaoanalyzer.runtime.MainDestinations.PARTICIPANTS_DETAIL_ROUTE
import me.hyuck.kakaoanalyzer.runtime.MainDestinations.STATISTICS_ROUTE

fun NavGraphBuilder.kakaoAnalyzerNavHost(
    onChatSelected: (String, NavBackStackEntry) -> Unit,
    onDetailParticipants: (String, NavBackStackEntry) -> Unit,
    onDetailKeywords: (String, NavBackStackEntry) -> Unit,
    upPress: () -> Unit
) {
    navigation(
        route = HOME_ROUTE,
        startDestination = HomeSections.CHATS.route
    ) {
        addHomeGraph(onChatSelected)
    }
    composable(
        route = "${STATISTICS_ROUTE}/{${CHAT_ID_KEY}}",
        arguments = listOf(navArgument(CHAT_ID_KEY) { type = NavType.StringType })
    ) { navBackStackEntry ->
        StatisticsScreen(
            onDetailParticipants = { id -> onDetailParticipants(id, navBackStackEntry) },
            onDetailKeywords = { id -> onDetailKeywords(id, navBackStackEntry) },
            upPress = upPress
        )
    }
    composable(
        route = "${KEYWORDS_DETAIL_ROUTE}/{${CHAT_ID_KEY}}",
        arguments = listOf(navArgument(CHAT_ID_KEY) { type = NavType.StringType })
    ) {
        DetailKeywordScreen(
            upPress = upPress
        )
    }
    composable(
        route = "${PARTICIPANTS_DETAIL_ROUTE}/{${CHAT_ID_KEY}}",
        arguments = listOf(navArgument(CHAT_ID_KEY) { type = NavType.StringType })
    ) {
        DetailScreen(
            detailType = DetailType.PARTICIPANT,
            upPress = upPress
        )
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