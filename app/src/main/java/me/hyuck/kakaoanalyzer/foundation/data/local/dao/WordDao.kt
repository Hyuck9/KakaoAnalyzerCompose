package me.hyuck.kakaoanalyzer.foundation.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.WordEntity

@Dao
interface WordDao {

	@Insert
	suspend fun saveWords(words: List<WordEntity>)

}