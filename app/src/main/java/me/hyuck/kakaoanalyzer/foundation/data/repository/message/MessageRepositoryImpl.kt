package me.hyuck.kakaoanalyzer.foundation.data.repository.message

import kotlinx.coroutines.CoroutineDispatcher
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.MessageDao
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.MessageEntity

class MessageRepositoryImpl(
	private val messageDao: MessageDao,
	private val ioDispatcher: CoroutineDispatcher
) : MessageRepository {

	override suspend fun saveMessages(messages: List<MessageEntity>) {
		TODO("Not yet implemented")
	}

	override suspend fun saveMessage(messageEntity: MessageEntity) {
		TODO("Not yet implemented")
	}

}