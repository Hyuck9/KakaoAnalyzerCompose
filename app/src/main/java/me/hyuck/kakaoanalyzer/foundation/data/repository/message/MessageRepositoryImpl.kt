package me.hyuck.kakaoanalyzer.foundation.data.repository.message

import kotlinx.coroutines.CoroutineDispatcher
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.MessageDao
import me.hyuck.kakaoanalyzer.model.Message
import me.hyuck.kakaoanalyzer.model.mapper.toMessageEntities
import me.hyuck.kakaoanalyzer.model.mapper.toMessageEntity

class MessageRepositoryImpl(
	private val messageDao: MessageDao,
	private val ioDispatcher: CoroutineDispatcher
) : MessageRepository {

	// TODO: 테스트 후 function 정리
	override suspend fun getMaxLine(chatId: String): Int {
		return messageDao.getMaxLine(chatId) ?: 0
	}

	override suspend fun saveMessages(messages: List<Message>) {
		messageDao.saveMessages(messages.toMessageEntities())
	}

	override suspend fun saveMessage(message: Message) {
		messageDao.saveMessage(message.toMessageEntity())
	}

}