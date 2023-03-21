package me.hyuck.kakaoanalyzer.features.statistics.participant.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.hyuck.kakaoanalyzer.features.base.StatefulViewModel
import me.hyuck.kakaoanalyzer.features.statistics.participant.data.IParticipantEnvironment
import me.hyuck.kakaoanalyzer.features.statistics.participant.data.ParticipantEnvironment
import me.hyuck.kakaoanalyzer.model.Chat
import javax.inject.Inject

@HiltViewModel
class ParticipantViewModel @Inject constructor(
	participantEnvironment: ParticipantEnvironment
) : StatefulViewModel<ParticipantState, Unit, Unit, IParticipantEnvironment>(ParticipantState(), participantEnvironment) {

	fun initChat(chat: Chat) {
		setState { copy(chat = chat) }
		initParticipants()
	}

	private fun initParticipants() {
		viewModelScope.launch {
			environment.getParticipants(state.value.chat, 10)
				.collect {
					setState { copy(items = it) }
				}
		}
	}

	override fun dispatch(action: Unit) {
	}
}