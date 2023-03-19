package me.hyuck.kakaoanalyzer.features.statistics.keyword.data

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.foundation.data.repository.word.WordRepository
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Keyword
import me.hyuck.kakaoanalyzer.model.Participant
import javax.inject.Inject

class KeywordEnvironment @Inject constructor(
	private val wordRepository: WordRepository
) : IKeywordEnvironment {

	override fun getKeywords(chat: Chat): Flow<List<Keyword>> = wordRepository.getKeywords(chat)


}