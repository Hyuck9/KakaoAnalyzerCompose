package me.hyuck.kakaoanalyzer.features.home.chats.data

import android.os.Environment
import timber.log.Timber
import java.io.File
import javax.inject.Inject

class ChatsEnvironment @Inject constructor(

) : IChatsEnvironment {

    private val txtFilePath = Environment.getExternalStorageDirectory().absolutePath + "/KakaoTalk/Chats"
    private val txtFileName = "KakaoTalkChats.txt"

    override fun test() {
        val txtFile = File(txtFilePath)
        val files = txtFile.listFiles()
        files?.forEach {
            val file = File(it.absolutePath + File.separator + txtFileName)
            Timber.i("file : $file}")
        }
    }

}