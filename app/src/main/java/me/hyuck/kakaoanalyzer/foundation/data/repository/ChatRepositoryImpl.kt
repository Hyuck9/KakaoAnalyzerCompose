package me.hyuck.kakaoanalyzer.foundation.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.ChatDao
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.ChatEntity
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.mapper.toChats

class ChatRepositoryImpl(
	private val chatDao: ChatDao,
	private val ioDispatcher: CoroutineDispatcher
) : ChatRepository {
	override suspend fun getChatById(chatId: String): Int {
		return chatDao.getChatById(chatId)
	}

	override fun getChats(): Flow<List<Chat>> {
		return chatDao.observeChats().map {
			it.toChats()
		}
	}

	override suspend fun saveChat(chatEntity: ChatEntity) = withContext(ioDispatcher) {
		chatDao.saveChat(chatEntity)
	}


}