package me.hyuck.kakaoanalyzer.features.statistics.participant.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.hyuck.kakaoanalyzer.features.base.StatefulViewModel
import me.hyuck.kakaoanalyzer.features.statistics.participant.data.IParticipantEnvironment
import me.hyuck.kakaoanalyzer.features.statistics.participant.data.ParticipantEnvironment
import me.hyuck.kakaoanalyzer.model.Chat
import timber.log.Timber
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
					if (state.value.isOneOnOne) {
						initOneOnOne()
					}
				}
		}
	}

	private fun initOneOnOne() {
		viewModelScope.launch {
			environment.getMessages(state.value.chat)
				.collect {
					setState { copy(messages = it) }

					val test = environment.analysisMessages(it)
					Timber.tag("TEST").i("user1Name : ${test.user1Name}")
					Timber.tag("TEST").i("user2Name : ${test.user2Name}")
					Timber.tag("TEST").i("user1MessageCount : ${test.user1MessageCount}")
					Timber.tag("TEST").i("user2MessageCount : ${test.user2MessageCount}")
					Timber.tag("TEST").i("user1FirstMessageCount : ${test.user1FirstMessageCount}")
					Timber.tag("TEST").i("user2FirstMessageCount : ${test.user2FirstMessageCount}")
					Timber.tag("TEST").i("user1FirstReplyTime : ${test.user1FirstReplyTime}")
					Timber.tag("TEST").i("user2FirstReplyTime : ${test.user2FirstReplyTime}")
					Timber.tag("TEST").i("user1AllReplyTime : ${test.user1AverageReplyTime}")
					Timber.tag("TEST").i("user2AllReplyTime : ${test.user2AverageReplyTime}")
					Timber.tag("TEST").i("allFirstReplyTime : ${test.allFirstReplyTime}")
					Timber.tag("TEST").i("allAverageReplyTime : ${test.allAverageReplyTime}")
				}
		}
	}

	override fun dispatch(action: Unit) {
	}
}