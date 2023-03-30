package me.hyuck.kakaoanalyzer.features.statistics.participant.ui

import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Message
import me.hyuck.kakaoanalyzer.model.Participant

data class ParticipantState(
	val chat: Chat = Chat(),
	val items: List<Participant> = listOf(),
	val messages: List<Message> = listOf()
) {
	val isOneOnOne: Boolean get() = items.size == 2
}