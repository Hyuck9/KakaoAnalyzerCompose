package me.hyuck.kakaoanalyzer.features.statistics.participant.data

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.foundation.data.repository.message.MessageRepository
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Message
import me.hyuck.kakaoanalyzer.model.OneOnOneAnalyticsInfo
import me.hyuck.kakaoanalyzer.model.Participant
import javax.inject.Inject

class ParticipantEnvironment @Inject constructor(
	private val messageRepository: MessageRepository
) : IParticipantEnvironment {

	override fun getParticipants(chat: Chat, limit: Int): Flow<List<Participant>> = messageRepository.getParticipants(chat, limit)

	override fun getMessages(chat: Chat, limit: Int): Flow<List<Message>> = messageRepository.getMessages(chat, limit)

	override fun analysisMessages(messages: List<Message>): OneOnOneAnalyticsInfo {
		return OneOnOneAnalyticsInfo(allMessages = messages)
	}

	private fun test() {

	}

}