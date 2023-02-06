package me.hyuck.kakaoanalyzer.foundation.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.ChatEntity

@Dao
interface ChatDao {

	@Query("SELECT COUNT(*) FROM chats WHERE chatId = :chatId")
	suspend fun getChatById(chatId: String): Int

	@Insert
	suspend fun saveChat(chatEntity: ChatEntity)

}