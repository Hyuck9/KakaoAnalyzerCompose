package me.hyuck.kakaoanalyzer.features.home.chats.data

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.model.Chat
import java.io.File

interface IChatsEnvironment {

    fun getChatList(): Flow<List<Chat>>

    // TODO: 테스트 후 function 정리
    suspend fun getMaxLine(chatId: String): Int

    suspend fun fileScan(localFilesDir: File)

    suspend fun analyzeChat(chat: Chat)
}