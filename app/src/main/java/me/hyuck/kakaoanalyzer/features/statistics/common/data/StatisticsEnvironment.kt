package me.hyuck.kakaoanalyzer.features.statistics.common.data

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.foundation.data.repository.chat.ChatRepository
import me.hyuck.kakaoanalyzer.model.Chat
import javax.inject.Inject

class StatisticsEnvironment @Inject constructor(
	private val chatRepository: ChatRepository
) : IStatisticsEnvironment {

	override fun getChatById(chatId: String): Flow<Chat> = chatRepository.getChatById(chatId)

}