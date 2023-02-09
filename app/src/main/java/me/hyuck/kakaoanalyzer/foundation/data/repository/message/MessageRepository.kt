package me.hyuck.kakaoanalyzer.foundation.data.repository.message

import me.hyuck.kakaoanalyzer.foundation.data.local.entity.MessageEntity

interface MessageRepository {

	suspend fun saveMessages(messages: List<MessageEntity>)

	suspend fun saveMessage(messageEntity: MessageEntity)

}