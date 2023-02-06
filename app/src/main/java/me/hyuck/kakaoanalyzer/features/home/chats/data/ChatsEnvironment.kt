package me.hyuck.kakaoanalyzer.features.home.chats.data

import android.os.Environment
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.ChatEntity
import me.hyuck.kakaoanalyzer.foundation.data.repository.ChatRepository
import me.hyuck.kakaoanalyzer.foundation.extension.toLocalDateTime
import timber.log.Timber
import java.io.File
import javax.inject.Inject

class ChatsEnvironment @Inject constructor(
    private val chatRepository: ChatRepository
) : IChatsEnvironment {

    private val txtFilePath = Environment.getExternalStorageDirectory().absolutePath + "/KakaoTalk/Chats"
    private val txtFileName = "KakaoTalkChats.txt"

    override fun fileScan(localFilesDir: File) {
        val files = localFilesDir.listFiles()
        files?.forEach {
            val file = File("${it.absoluteFile}/$txtFileName")
            Timber.tag("TEST").i("file : ${file.canRead()} - ${file.parentFile?.name} - ${file.lastModified()}")
            saveChat(file)
        }
    }

    private fun saveChat(chatsFile: File) {
        val chatId = chatsFile.parentFile?.name ?: return
        // TODO: chatId 조회 후 있으면 Pass, 없으면 아래 Insert 로직 수행
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
                // TODO: Chats Insert
            } else {
                chatsFile.parentFile?.delete() ?: chatsFile.delete()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}