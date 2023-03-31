package me.hyuck.kakaoanalyzer.features.statistics.participant.data

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.foundation.data.repository.message.MessageRepository
import me.hyuck.kakaoanalyzer.foundation.extension.durationHoursTo
import me.hyuck.kakaoanalyzer.foundation.extension.durationSecondsTo
import me.hyuck.kakaoanalyzer.foundation.extension.toLocalDateTime
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Message
import me.hyuck.kakaoanalyzer.model.Participant
import me.hyuck.kakaoanalyzer.model.ReplyInfo
import java.util.*
import javax.inject.Inject

class ParticipantEnvironment @Inject constructor(
	private val messageRepository: MessageRepository
) : IParticipantEnvironment {

	override fun getParticipants(chat: Chat, limit: Int): Flow<List<Participant>> = messageRepository.getParticipants(chat, limit)

	override fun getMessages(chat: Chat, limit: Int): Flow<List<Message>> = messageRepository.getMessages(chat, limit)

	override fun analysisMessages(messages: List<Message>) {
		var preUser = "me.hyuck.kakaoanalyzer.features.statistics.participant.preUser"
		var isFirst = true
		var preDate = Date(0).toLocalDateTime()
		val firstMessages = mutableListOf<Message>()
		val firstReplies = mutableListOf<ReplyInfo>()
		val allReplies = mutableListOf<ReplyInfo>()

		messages.forEach { message ->
			if (message.dateTime.durationHoursTo(preDate) >= 1) {
				firstMessages.add(message)
				isFirst = true
			} else if ( preUser != message.userName ) {
				val replyInfo = ReplyInfo(
					userName = message.userName,
					replyTime = message.dateTime.durationSecondsTo(preDate)
				)
				if (isFirst) {
					firstReplies.add(replyInfo)
					isFirst = false
				}
				allReplies.add(replyInfo)
			}
			preUser = message.userName
			preDate = message.dateTime
		}
	}

}