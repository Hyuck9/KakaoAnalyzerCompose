package me.hyuck.kakaoanalyzer.features.home.chats.data

import java.io.File

interface IChatsEnvironment {
    fun fileScan(localFilesDir: File)
}