package me.hyuck.kakaoanalyzer.foundation.data.repository.word

import me.hyuck.kakaoanalyzer.model.Word

interface WordRepository {

	suspend fun saveWords(words: List<Word>)

}