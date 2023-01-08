package me.hyuck.kakaoanalyzer.foundation.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.ChatEntity

@Dao
interface ChatDao {

	@Insert
	suspend fun saveChat(chatEntity: ChatEntity)

}