package me.hyuck.kakaoanalyzer.features.statistics.participant.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.hyuck.kakaoanalyzer.model.Chat

@Composable
fun ParticipantScreen(
	viewModel: ParticipantViewModel = hiltViewModel(),
	chat: Chat,
) {
	viewModel.initChat(chat)
	val state by viewModel.state.collectAsStateWithLifecycle()

	Text(
		text = "ParticipantScreen  - ${chat.id}"
	)
}