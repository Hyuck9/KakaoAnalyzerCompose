package me.hyuck.kakaoanalyzer.foundation.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.MessageEntity
import me.hyuck.kakaoanalyzer.model.Participant
import me.hyuck.kakaoanalyzer.model.TimeZone
import java.time.LocalDateTime

@Dao
interface MessageDao {

	@Query("SELECT COUNT(DISTINCT userName) FROM messages WHERE chatId = :chatId AND dateTime BETWEEN :startDate AND :endDate")
	fun countUserById(chatId: String, startDate: LocalDateTime, endDate: LocalDateTime): Flow<Int>

	@Query("SELECT COUNT(*) FROM messages WHERE chatId = :chatId AND dateTime BETWEEN :startDate AND :endDate")
	fun countMessages(chatId: String, startDate: LocalDateTime, endDate: LocalDateTime): Flow<Int>

	@Query(
		"""
			SELECT 
				userName, 
				COUNT(*) AS messageCount,
				MIN(dateTime) AS firstDate,
				MAX(dateTime) AS lastDate
			FROM messages
			WHERE chatId = :chatId AND dateTime BETWEEN :startDate AND :endDate
			GROUP BY userName
			ORDER BY messageCount DESC
			LIMIT :limit
		"""
	)
	fun observeParticipants(chatId: String, startDate: LocalDateTime, endDate: LocalDateTime, limit: Int = -1): Flow<List<Participant>>

	@Query("""
		SELECT hour, COUNT(*) AS messageCount 
		FROM messages 
		WHERE chatId = :chatId AND dateTime BETWEEN :startDate AND :endDate 
		GROUP BY hour
		"""
	)
	fun observeMessageCountByTimeZone(chatId: String, startDate: LocalDateTime, endDate: LocalDateTime): Flow<List<TimeZone>>

	@Insert
	suspend fun saveMessages(messages: List<MessageEntity>)
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun saveMessage(messageEntity: MessageEntity)


}