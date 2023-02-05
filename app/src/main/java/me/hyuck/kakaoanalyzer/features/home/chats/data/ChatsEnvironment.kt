package me.hyuck.kakaoanalyzer.features.home.chats.data

import android.content.ContextWrapper
import android.os.Environment
import timber.log.Timber
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import javax.inject.Inject

class ChatsEnvironment @Inject constructor(

) : IChatsEnvironment {

    private val txtFilePath = Environment.getExternalStorageDirectory().absolutePath + "/KakaoTalk/Chats"
    private val txtFileName = "KakaoTalkChats.txt"

    override fun test() {
//        val txtFile = File(txtFilePath)
//        val files = txtFile.listFiles()
//        files?.forEach {
//            val file = File(it.absolutePath + File.separator + txtFileName)
//            Timber.tag("TEST").i("file : $file")
//            test(file)
//        }
    }

//    private fun test(file: File) {
//        try {
//            BufferedReader(FileReader(file)).use { br ->
//                val lines = br.lines().count()
//                val title = br.readLine()
//                Timber.tag("TEST").i("lines : $lines - title : $title")
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }


}