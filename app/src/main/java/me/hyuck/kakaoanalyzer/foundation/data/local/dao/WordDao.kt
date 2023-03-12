package me.hyuck.kakaoanalyzer.foundation.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.WordEntity
import java.time.LocalDateTime

@Dao
interface WordDao {

	@Insert
	suspend fun saveWords(words: List<WordEntity>)

	@Query("SELECT COUNT(*) FROM words WHERE chatId = :chatId AND dateTime BETWEEN :startDate AND :endDate")
	fun countKeywordById(chatId: String, startDate: LocalDateTime, endDate: LocalDateTime): Flow<Int>

}