package me.hyuck.kakaoanalyzer.features.statistics.time.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.hyuck.kakaoanalyzer.model.Chat

@Composable
fun TimeZoneScreen(
    viewModel: TimeZoneViewModel = hiltViewModel(),
    chat: Chat
) {
    viewModel.initChat(chat)
    val state by viewModel.state.collectAsStateWithLifecycle()

}