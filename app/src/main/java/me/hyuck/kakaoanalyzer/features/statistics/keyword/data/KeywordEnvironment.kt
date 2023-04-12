package me.hyuck.kakaoanalyzer.features.statistics.keyword.data

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.foundation.data.repository.message.MessageRepository
import me.hyuck.kakaoanalyzer.foundation.data.repository.word.WordRepository
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Filter
import me.hyuck.kakaoanalyzer.model.Keyword
import me.hyuck.kakaoanalyzer.model.toFilterNames
import javax.inject.Inject

class KeywordEnvironment @Inject constructor(
	private val messageRepository: MessageRepository,
	private val wordRepository: WordRepository
) : IKeywordEnvironment {

	override fun getUsers(chat: Chat): Flow<List<String>> = messageRepository.getUserNames(chat)

	override fun getKeywords(chat: Chat, filters: List<Filter>, limit: Int): Flow<List<Keyword>> {
		val userNames = filters.toFilterNames()
		return wordRepository.getKeywords(chat, userNames, limit)
	}


}