package me.hyuck.kakaoanalyzer.foundation.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.ChatEntity
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.ChatStatus
import java.time.LocalDateTime

@Dao
interface ChatDao {

	@Query("SELECT COUNT(*) FROM chats WHERE chatId = :chatId")
	suspend fun getChatById(chatId: String): Int

	@Query("SELECT * FROM chats")
	fun observeChats(): Flow<List<ChatEntity>>

	/*@Query("SELECT a.chatId, a.chatTitle, a.chatStatus," +
			" a.saveDate, a.fileSize, a.filePath, IFNULL(MAX(b.lineNumber), 0) AS analysisLine," +
			" a.lineSize, a.startDate, a.endDate, a.createdAt, a.updatedAt" +
			" FROM chats a" +
			" LEFT JOIN messages b ON a.chatId = b.chatId" +
			" GROUP BY a.chatId")*/
	@Query("SELECT a.chatId AS id, a.chatTitle AS title, a.chatStatus as status," +
			" a.saveDate, a.fileSize, a.filePath as path, IFNULL(MAX(b.lineNumber), 0) AS analysisLine," +
			" a.lineSize, a.startDate, a.endDate, a.createdAt, a.updatedAt," +
			" ((IFNULL(MAX(b.lineNumber), 0) * 1.0 ) / a.lineSize * 100.0) AS progress" +
			" FROM chats a" +
			" LEFT JOIN messages b ON a.chatId = b.chatId" +
			" GROUP BY a.chatId")
	fun observeChatsTest(): Flow<List<Chat>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun saveChat(chatEntity: ChatEntity)

	@Query("UPDATE chats SET chatStatus = :chatStatus, updatedAt = :updatedAt WHERE chatId = :chatId")
	suspend fun updateStatus(chatId: String, chatStatus: ChatStatus, updatedAt: LocalDateTime)

	@Query("UPDATE chats SET analysisLine = :currentLine, updatedAt = :updatedAt WHERE chatId = :chatId")
	suspend fun updateProgress(chatId: String, currentLine: Int, updatedAt: LocalDateTime)

}