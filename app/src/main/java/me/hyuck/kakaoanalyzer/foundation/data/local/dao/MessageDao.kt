package me.hyuck.kakaoanalyzer.foundation.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.MessageEntity

@Dao
interface MessageDao {

	// TODO: 테스트 후 function 정리
	@Query("SELECT MAX(lineNumber) FROM messages WHERE chatId = :chatId GROUP BY chatId")
	suspend fun getMaxLine(chatId: String): Int?

	@Insert
	suspend fun saveMessages(messages: List<MessageEntity>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun saveMessage(messageEntity: MessageEntity)

}