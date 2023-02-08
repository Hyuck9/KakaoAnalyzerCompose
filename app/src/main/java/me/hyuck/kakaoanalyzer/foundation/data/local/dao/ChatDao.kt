package me.hyuck.kakaoanalyzer.foundation.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.ChatEntity
import me.hyuck.kakaoanalyzer.model.ChatStatus
import java.time.LocalDateTime

@Dao
interface ChatDao {

	@Query("SELECT COUNT(*) FROM chats WHERE chatId = :chatId")
	suspend fun getChatById(chatId: String): Int

	@Query("SELECT * FROM chats")
	fun observeChats(): Flow<List<ChatEntity>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun saveChat(chatEntity: ChatEntity)

	@Query("UPDATE chats SET chatStatus = :chatStatus, updatedAt = :updatedAt WHERE chatId = :chatId")
	suspend fun updateStatus(chatId: String, chatStatus: ChatStatus, updatedAt: LocalDateTime)

	@Query("UPDATE chats SET analysisLine = :currentLine, updatedAt = :updatedAt WHERE chatId = :chatId")
	suspend fun updateProgress(chatId: String, currentLine: Int, updatedAt: LocalDateTime)

}