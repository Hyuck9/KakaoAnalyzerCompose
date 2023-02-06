package me.hyuck.kakaoanalyzer.foundation.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.ChatEntity

@Dao
interface ChatDao {

	@Query("SELECT COUNT(*) FROM chats WHERE chatId = :chatId")
	suspend fun getChatById(chatId: String): Int

	@Query("SELECT * FROM chats")
	fun observeChats(): Flow<List<ChatEntity>>

	@Insert
	suspend fun saveChat(chatEntity: ChatEntity)

}