package me.hyuck.kakaoanalyzer.features.detail.keyword.data

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.foundation.data.repository.chat.ChatRepository
import me.hyuck.kakaoanalyzer.model.Chat
import javax.inject.Inject

class DetailKeywordEnvironment @Inject constructor(
    private val chatRepository: ChatRepository
) : IDetailKeywordEnvironment {

    override fun getChatById(chatId: String): Flow<Chat> = chatRepository.getChatById(chatId)

}