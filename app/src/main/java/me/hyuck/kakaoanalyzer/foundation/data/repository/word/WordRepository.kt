package me.hyuck.kakaoanalyzer.foundation.data.repository.word

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Word

interface WordRepository {

	fun countKeywordById(chat: Chat): Flow<Int>

	suspend fun saveWords(words: List<Word>)

}