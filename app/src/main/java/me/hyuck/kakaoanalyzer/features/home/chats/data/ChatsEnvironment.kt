package me.hyuck.kakaoanalyzer.features.home.chats.data

import android.os.Environment
import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.ChatEntity
import me.hyuck.kakaoanalyzer.foundation.data.repository.ChatRepository
import me.hyuck.kakaoanalyzer.foundation.extension.toLocalDateTime
import me.hyuck.kakaoanalyzer.model.Chat
import timber.log.Timber
import java.io.File
import javax.inject.Inject

class ChatsEnvironment @Inject constructor(
    private val chatRepository: ChatRepository
) : IChatsEnvironment {

    private val txtFilePath = Environment.getExternalStorageDirectory().absolutePath + "/KakaoTalk/Chats"
    private val txtFileName = "KakaoTalkChats.txt"

    override fun getChatList(): Flow<List<Chat>> = chatRepository.getChats()

    override suspend fun fileScan(localFilesDir: File) {
        val files = localFilesDir.listFiles()
        files?.forEach {
            val file = File("${it.absoluteFile}/$txtFileName")
            saveChat(file)
        }
    }

    private suspend fun saveChat(chatsFile: File) {
        val chatId = chatsFile.parentFile?.name ?: return
        if (chatRepository.getChatById(chatId) > 0) return
        try {
            val readLines = chatsFile.readLines()
            if (readLines.size > 5) {
                val chatEntity = ChatEntity(
                    id = chatId,
                    title = readLines[0],
                    saveDate = readLines[1],
                    fileSize = chatsFile.length(),
                    lineSize = readLines.size,
                    path = chatsFile.absolutePath,
                    startDate = readLines[4].toLocalDateTime()
                )
                Timber.tag("TEST").i(chatEntity.toString())
                chatRepository.saveChat(chatEntity)
            } else {
                chatsFile.parentFile?.delete() ?: chatsFile.delete()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}