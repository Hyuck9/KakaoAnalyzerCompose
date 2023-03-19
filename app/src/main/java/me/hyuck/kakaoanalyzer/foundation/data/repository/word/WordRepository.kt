package me.hyuck.kakaoanalyzer.foundation.data.repository.word

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Keyword
import me.hyuck.kakaoanalyzer.model.Word

interface WordRepository {

	fun countKeywordById(chat: Chat): Flow<Int>

	fun getKeywords(chat: Chat, limit: Int = -1): Flow<List<Keyword>>

	suspend fun saveWords(words: List<Word>)

}