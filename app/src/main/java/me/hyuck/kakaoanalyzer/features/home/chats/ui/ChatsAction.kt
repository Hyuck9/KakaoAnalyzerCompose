package me.hyuck.kakaoanalyzer.features.home.chats.ui

import java.io.File

sealed class ChatsAction {
    data class FileScan(val localFilesDir: File) : ChatsAction()
}