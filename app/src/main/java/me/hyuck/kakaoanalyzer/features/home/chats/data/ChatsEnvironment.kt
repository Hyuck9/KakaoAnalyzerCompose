package me.hyuck.kakaoanalyzer.features.home.chats.data

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.ChatEntity
import me.hyuck.kakaoanalyzer.foundation.data.repository.chat.ChatRepository
import me.hyuck.kakaoanalyzer.foundation.data.repository.message.MessageRepository
import me.hyuck.kakaoanalyzer.foundation.data.repository.word.WordRepository
import me.hyuck.kakaoanalyzer.foundation.extension.isFirstDateTimeMessage
import me.hyuck.kakaoanalyzer.foundation.extension.isNotDateTimeMessage
import me.hyuck.kakaoanalyzer.foundation.extension.toLocalDateTime
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.ChatStatus
import timber.log.Timber
import java.io.File
import javax.inject.Inject

class ChatsEnvironment @Inject constructor(
    private val chatRepository: ChatRepository,
    private val messageRepository: MessageRepository,
    private val wordRepository: WordRepository,
) : IChatsEnvironment {

    private val txtFileName = "KakaoTalkChats.txt"

    override fun getChatList(): Flow<List<Chat>> = chatRepository.getChats()

    override suspend fun fileScan(localFilesDir: File) {
        val files = localFilesDir.listFiles()
        files?.forEach {
            val file = File("${it.absoluteFile}/$txtFileName")
            saveChat(file)
        }
    }

    override suspend fun analyzeChat(chat: Chat) {
        chatRepository.updateStatus(chat.id, ChatStatus.IN_PROGRESS)
        try {
            parseFile(chat)
            chatRepository.updateStatus(chat.id, ChatStatus.ANALYSIS_COMPLETE)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }




    private suspend fun saveChat(chatFile: File) {
        val chatId = chatFile.parentFile?.name ?: return
        if (chatRepository.getChatById(chatId) > 0) return
        try {
            val readLines = chatFile.readLines()
            if (readLines.size > 5) {
                val chatEntity = ChatEntity(
                    id = chatId,
                    title = readLines[0],
                    saveDate = readLines[1],
                    fileSize = chatFile.length(),
                    lineSize = readLines.size,
                    path = chatFile.absolutePath,
                    startDate = readLines[4].toLocalDateTime()
                )
                Timber.tag("TEST").i(chatEntity.toString())
                chatRepository.saveChat(chatEntity)
            } else {
                chatFile.parentFile?.delete() ?: chatFile.delete()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Throws(Exception::class)
    private suspend fun parseFile(chat: Chat) {
        val chatFile = File(chat.path)
        val readLines = chatFile.readLines()
        var message: StringBuilder? = null
        readLines.forEachIndexed { index, line ->
            if (index >= 5 ) {
                if (line.isFirstDateTimeMessage()) {
                    message?.let {
                        Timber.tag("TEST").i("완성된 메시지 : ${it.toString().trim()}")
                    } ?: Timber.tag("TEST").i("message is null")
                    message = StringBuilder(line)
                } else {
                    if ( line.isNotDateTimeMessage() ) {
                        if (line.isEmpty()) {   // 개행된 메시지 이므로 이전 메시지 뒤에 붙이기
                            message?.append(" $line")
                        }
                    }
                }
            }
            chatRepository.updateProgress(chat.id, index + 1)
        }

        message?.let {
            Timber.tag("TEST").i("파싱 완료 후 완성된 메시지 : ${it.toString().trim()}")
        } ?: Timber.tag("TEST").i("파싱 완료 후 message is null")
    }

    private suspend fun parseMessage(msg: String) {

    }


}