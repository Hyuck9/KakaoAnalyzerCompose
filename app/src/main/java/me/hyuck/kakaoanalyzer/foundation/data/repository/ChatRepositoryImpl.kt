package me.hyuck.kakaoanalyzer.foundation.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.ChatDao
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.ChatEntity

class ChatRepositoryImpl(
	private val chatDao: ChatDao,
	private val ioDispatcher: CoroutineDispatcher
) : ChatRepository {
	override suspend fun getChatById(chatId: String): Int {
		return chatDao.getChatById(chatId)
	}

	override suspend fun saveChat(chatEntity: ChatEntity) = withContext(ioDispatcher) {
		chatDao.saveChat(chatEntity)
	}


}