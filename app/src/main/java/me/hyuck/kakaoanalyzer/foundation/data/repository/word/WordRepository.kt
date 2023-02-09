package me.hyuck.kakaoanalyzer.foundation.data.repository.word

import me.hyuck.kakaoanalyzer.foundation.data.local.entity.WordEntity

interface WordRepository {

	suspend fun saveWords(words: List<WordEntity>)

}