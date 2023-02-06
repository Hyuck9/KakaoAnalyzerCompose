package me.hyuck.kakaoanalyzer.features.home.chats.data

import java.io.File

interface IChatsEnvironment {
    suspend fun fileScan(localFilesDir: File)
}