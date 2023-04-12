package me.hyuck.kakaoanalyzer.features.detail.keyword.data

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.model.Chat

interface IDetailKeywordEnvironment {

    fun getChatById(chatId: String): Flow<Chat>

}