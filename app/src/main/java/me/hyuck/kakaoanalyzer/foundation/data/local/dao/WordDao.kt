package me.hyuck.kakaoanalyzer.foundation.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.WordEntity
import me.hyuck.kakaoanalyzer.model.Keyword
import java.time.LocalDateTime

@Dao
interface WordDao {

	@Insert
	suspend fun saveWords(words: List<WordEntity>)

	@Query("SELECT COUNT(*) FROM words WHERE chatId = :chatId AND dateTime BETWEEN :startDate AND :endDate")
	fun countKeywordById(chatId: String, startDate: LocalDateTime, endDate: LocalDateTime): Flow<Int>

	@Query("""
		SELECT word, COUNT(*) AS wordCount 
		FROM words 
		WHERE chatId = :chatId AND dateTime BETWEEN :startDate AND :endDate
		GROUP BY word
		ORDER BY wordCount DESC
		LIMIT :limit
		""")
	fun getKeywords(chatId: String, startDate: LocalDateTime, endDate: LocalDateTime, limit: Int = -1): Flow<List<Keyword>>

	@Query("""
		SELECT word, COUNT(*) AS wordCount 
		FROM words 
		WHERE chatId = :chatId AND dateTime BETWEEN :startDate AND :endDate AND  userName IN (:filters)
		GROUP BY word
		ORDER BY wordCount DESC
		LIMIT :limit
		""")
	fun getKeywordsByFilters(chatId: String, startDate: LocalDateTime, endDate: LocalDateTime, filters: List<String>, limit: Int = -1): Flow<List<Keyword>>

	@Query("""
		SELECT word, COUNT(*) AS wordCount 
		FROM words 
		WHERE chatId = :chatId AND word LIKE :query
		GROUP BY word
		ORDER BY wordCount DESC
		""")
	fun getKeywords(chatId: String, query: String): PagingSource<Int, Keyword>
//	fun getKeywords(chatId: String, query: String): Flow<List<Keyword>>

	@Query("""
		SELECT word, COUNT(*) AS wordCount 
		FROM words 
		WHERE chatId = :chatId AND word LIKE :query AND  userName IN (:filters)
		GROUP BY word
		ORDER BY wordCount DESC
		""")
	fun getKeywordsByFilters(chatId: String, filters: List<String>, query: String): PagingSource<Int, Keyword>
//	fun getKeywordsByFilters(chatId: String, filters: List<String>, query: String): Flow<List<Keyword>>

}