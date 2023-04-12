package me.hyuck.kakaoanalyzer.foundation.data.repository.word

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.WordDao
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Keyword
import me.hyuck.kakaoanalyzer.model.Word
import me.hyuck.kakaoanalyzer.model.mapper.toWordEntities

class WordRepositoryImpl(
	private val wordDao: WordDao,
	private val ioDispatcher: CoroutineDispatcher
) : WordRepository {

	override fun countKeywordById(chat: Chat): Flow<Int> {
		return wordDao.countKeywordById(chat.id, chat.startDate, chat.endDate)
	}

	override fun getKeywords(chat: Chat, filters: List<String>, limit: Int): Flow<List<Keyword>> {
		return if (filters.isEmpty()) {
			wordDao.getKeywords(chat.id, chat.startDate, chat.endDate, limit)
		} else {
			wordDao.getKeywordsByFilters(chat.id, chat.startDate, chat.endDate, filters, limit)
		}
	}

	override fun getKeywords(chatId: String, filters: List<String>, query: String): Flow<List<Keyword>> {
		return if (filters.isEmpty()) {
			wordDao.getKeywords(chatId, "%$query%")
		} else {
			wordDao.getKeywordsByFilters(chatId, filters, "%$query%")
		}
	}

	override suspend fun saveWords(words: List<Word>) = withContext(ioDispatcher) {
		wordDao.saveWords(words.toWordEntities())
	}

}