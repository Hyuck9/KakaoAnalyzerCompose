package me.hyuck.kakaoanalyzer.foundation.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.MessageEntity

@Dao
interface MessageDao {

	@Insert
	suspend fun saveMessages(messages: List<MessageEntity>)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun saveMessage(messageEntity: MessageEntity)

}