package me.hyuck.kakaoanalyzer.features.home.chats.data

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.ChatEntity
import me.hyuck.kakaoanalyzer.foundation.data.repository.chat.ChatRepository
import me.hyuck.kakaoanalyzer.foundation.data.repository.message.MessageRepository
import me.hyuck.kakaoanalyzer.foundation.data.repository.word.WordRepository
import me.hyuck.kakaoanalyzer.foundation.extension.*
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.ChatStatus
import me.hyuck.kakaoanalyzer.model.Message
import me.hyuck.kakaoanalyzer.model.Word
import java.io.File
import javax.inject.Inject

class ChatsEnvironment @Inject constructor(
    private val chatRepository: ChatRepository,
    private val messageRepository: MessageRepository,
    private val wordRepository: WordRepository,
) : IChatsEnvironment {

    private val txtFileName = "KakaoTalkChats.txt"

    private lateinit var messages: MutableList<Message>
    private lateinit var words: MutableList<Word>

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
            saveAndFlushMessagesAndWords()
            chatRepository.updateStatus(chat.id, ChatStatus.ANALYSIS_COMPLETE)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }




    private suspend fun saveChat(chatFile: File) {
        val chatId = chatFile.parentFile?.name ?: return
        if (chatRepository.countChatById(chatId) > 0) return
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
        messages = mutableListOf()
        words = mutableListOf()
        val chatFile = File(chat.path)
        val readLines = chatFile.readLines()
        var message: StringBuilder? = null
        readLines.forEachIndexed { index, line ->
            if (index >= 5 ) {
                if (line.isFirstDateTimeMessage()) {
                    message?.let {
                        parseMessage(chat.id, it.toString(), index + 1)
                    } ?: testLog("message is null")
                    message = StringBuilder(line)
                } else {
                    if ( line.isNotDateTimeMessage() ) {
                        if (line.isNotEmpty()) {   // 개행된 메시지 이므로 이전 메시지 뒤에 붙이기
                            message?.append("\n$line")
                        }
                    }
                }
            }
        }

        message?.let {
            testLog("파싱 완료 후 완성된 메시지 : $it")
            parseMessage(chat.id, it.toString(), readLines.size)
        } ?: testLog("파싱 완료 후 message is null")
    }

    private suspend fun parseMessage(chatId: String, msg: String, currentLine: Int) {
        if (msg.isPassedInOutMessage()) return
        val firstSplitIndex = msg.indexOf(" : ")
        val dateAndName = msg.split(" : ")[0]
        val dateTime = dateAndName.split(", ")[0].toLocalDateTime()
        val userName = dateAndName.split(", ")[1]
        val content = msg.substring(firstSplitIndex + 3)
        val message = Message(
            chatId = chatId,
            dateTime = dateTime,
            userName = userName,
            lineNumber = currentLine,
            content = content,
            hour = dateTime.hour
        )
//        messageRepository.saveMessage(message)
//        wordRepository.saveWords(message.parseContentToWords())
//        chatRepository.updateProgress(chatId, currentLine)
        if (messages.size > 3000 || words.size > 5000) {
            saveAndFlushMessagesAndWords()
        }
        messages.add(message)
        words.addAll(message.parseContentToWords())
    }

    private suspend fun saveAndFlushMessagesAndWords() {
        testLog("messages : ${messages.size}")
        testLog("words : ${words.size}")
        messageRepository.saveMessages(messages)
        wordRepository.saveWords(words)
        messages = mutableListOf()
        words = mutableListOf()
    }

}