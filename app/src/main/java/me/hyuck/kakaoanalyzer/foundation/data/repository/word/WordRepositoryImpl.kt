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

	override fun getKeywords(chat: Chat, limit: Int): Flow<List<Keyword>> {
		return wordDao.getKeywords(chat.id, chat.startDate, chat.endDate, limit)
	}

	override suspend fun saveWords(words: List<Word>) = withContext(ioDispatcher) {
		wordDao.saveWords(words.toWordEntities())
	}

}