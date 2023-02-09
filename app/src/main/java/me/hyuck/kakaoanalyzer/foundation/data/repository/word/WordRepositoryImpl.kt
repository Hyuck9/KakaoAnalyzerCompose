package me.hyuck.kakaoanalyzer.foundation.data.repository.word

import kotlinx.coroutines.CoroutineDispatcher
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.WordDao
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.WordEntity

class WordRepositoryImpl(
	private val wordDao: WordDao,
	private val ioDispatcher: CoroutineDispatcher
) : WordRepository {

	override suspend fun saveWords(words: List<WordEntity>) {
		TODO("Not yet implemented")
	}

}