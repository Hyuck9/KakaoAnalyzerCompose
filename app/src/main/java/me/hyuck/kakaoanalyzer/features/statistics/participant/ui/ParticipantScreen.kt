package me.hyuck.kakaoanalyzer.features.statistics.participant.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.hyuck.kakaoanalyzer.foundation.uicomponent.ParticipantItemCell
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Participant

@Composable
fun ParticipantScreen(
	viewModel: ParticipantViewModel = hiltViewModel(),
	chat: Chat,
) {
	viewModel.initChat(chat)
	val state by viewModel.state.collectAsStateWithLifecycle()

	ParticipantContent(participants = state.items)
}

@Composable
private fun ParticipantContent(
	modifier: Modifier = Modifier,
	participants: List<Participant>,
) {
	LazyColumn(
		modifier = modifier
			.fillMaxSize()
			.padding(all = 16.dp)
	) {
		items(
			items = participants,
			key = { item -> item.userName }
		) { participant ->
			ParticipantItemCell(participant = participant)
		}
	}
}