package me.hyuck.kakaoanalyzer.foundation.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.MessageEntity

@Dao
interface MessageDao {

	@Insert
	suspend fun saveMessages(messages: List<MessageEntity>)

}