package me.hyuck.kakaoanalyzer.features.statistics.participant.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import me.hyuck.kakaoanalyzer.model.Chat

@Composable
fun ParticipantScreen(
	viewModel: ParticipantViewModel = hiltViewModel(),
	chat: Chat,
) {
	Text(
		text = "ParticipantScreen  - ${chat.id}"
	)
}