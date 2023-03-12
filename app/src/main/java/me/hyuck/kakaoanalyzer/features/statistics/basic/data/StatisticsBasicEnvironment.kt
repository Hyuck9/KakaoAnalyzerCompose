package me.hyuck.kakaoanalyzer.features.statistics.basic.data

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.foundation.data.repository.message.MessageRepository
import me.hyuck.kakaoanalyzer.foundation.data.repository.word.WordRepository
import me.hyuck.kakaoanalyzer.model.Chat
import javax.inject.Inject

class StatisticsBasicEnvironment @Inject constructor(
	private val messageRepository: MessageRepository,
	private val wordRepository: WordRepository,
) : IStatisticsBasicEnvironment {

	override fun getUserCount(chat: Chat): Flow<Int> = messageRepository.countUserById(chat)

	override fun getMessageCount(chat: Chat): Flow<Int> = messageRepository.countMessages(chat)

	override fun getKeywordCount(chat: Chat): Flow<Int> = wordRepository.countKeywordById(chat)

}