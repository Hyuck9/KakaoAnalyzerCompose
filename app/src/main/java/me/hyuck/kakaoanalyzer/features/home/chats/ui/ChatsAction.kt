package me.hyuck.kakaoanalyzer.features.home.chats.ui

import me.hyuck.kakaoanalyzer.model.Chat
import java.io.File

sealed class ChatsAction {
    data class FileScan(val localFilesDir: File) : ChatsAction()
    data class AnalyzeChat(val chat: Chat) : ChatsAction()
}