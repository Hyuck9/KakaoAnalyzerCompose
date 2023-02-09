package me.hyuck.kakaoanalyzer.foundation.data.repository.word

import kotlinx.coroutines.CoroutineDispatcher
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.WordDao
import me.hyuck.kakaoanalyzer.model.Word
import me.hyuck.kakaoanalyzer.model.mapper.toWordEntities

class WordRepositoryImpl(
	private val wordDao: WordDao,
	private val ioDispatcher: CoroutineDispatcher
) : WordRepository {

	override suspend fun saveWords(words: List<Word>) {
		wordDao.saveWords(words.toWordEntities())
	}

}