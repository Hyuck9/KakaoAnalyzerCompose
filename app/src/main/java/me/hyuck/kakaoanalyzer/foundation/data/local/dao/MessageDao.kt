package me.hyuck.kakaoanalyzer.foundation.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.MessageEntity
import java.time.LocalDateTime

@Dao
interface MessageDao {

	@Query("SELECT COUNT(DISTINCT userName) FROM messages WHERE chatId = :chatId AND dateTime BETWEEN :startDate AND :endDate")
	fun countUserById(chatId: String, startDate: LocalDateTime, endDate: LocalDateTime): Flow<Int>

	@Query("SELECT COUNT(*) FROM messages WHERE chatId = :chatId AND dateTime BETWEEN :startDate AND :endDate")
	fun countMessages(chatId: String, startDate: LocalDateTime, endDate: LocalDateTime): Flow<Int>

	@Insert
	suspend fun saveMessages(messages: List<MessageEntity>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun saveMessage(messageEntity: MessageEntity)



}