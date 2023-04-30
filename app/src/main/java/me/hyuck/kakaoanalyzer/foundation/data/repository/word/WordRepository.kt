package me.hyuck.kakaoanalyzer.foundation.data.repository.word

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Keyword
import me.hyuck.kakaoanalyzer.model.Word

interface WordRepository {

	fun countKeywordById(chat: Chat): Flow<Int>

	fun getKeywords(chat: Chat, filters: List<String>, limit: Int = -1): Flow<List<Keyword>>

	fun getKeywords(chatId: String, filters: List<String>, query: String = ""): PagingSource<Int, Keyword>

	suspend fun saveWords(words: List<Word>)

}