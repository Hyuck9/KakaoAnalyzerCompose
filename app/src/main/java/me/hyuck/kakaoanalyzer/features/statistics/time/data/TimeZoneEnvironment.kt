package me.hyuck.kakaoanalyzer.features.statistics.time.data

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.foundation.data.repository.message.MessageRepository
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.TimeZone
import javax.inject.Inject

class TimeZoneEnvironment @Inject constructor(
	private val messageRepository: MessageRepository
) : ITimeZoneEnvironment {

	override fun getMessageCountByTimeZone(chat: Chat): Flow<List<TimeZone>> = messageRepository.getMessageCountByTimeZone(chat)

}