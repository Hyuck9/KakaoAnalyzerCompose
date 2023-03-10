package me.hyuck.kakaoanalyzer.features.home.chats.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.hyuck.kakaoanalyzer.features.base.StatefulViewModel
import me.hyuck.kakaoanalyzer.features.home.chats.data.IChatsEnvironment
import me.hyuck.kakaoanalyzer.foundation.extension.testLog
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    chatsEnvironment: IChatsEnvironment
) : StatefulViewModel<ChatsState, Unit, ChatsAction, IChatsEnvironment>(ChatsState(), chatsEnvironment) {

    init {
        initChats()
    }

    private fun initChats() {
        viewModelScope.launch {
            environment.getChatList().collect {
//                it.forEach {  chat ->
//                    testLog("$chat, MaxLine : ${environment.getMaxLine(chat.id)}")
//                }
//                val test = it.map { chat ->
//                    chat.copy(analysisLine = environment.getMaxLine(chat.id))
//                }
                setState { copy(items = it.toMutableList()) }
            }
        }
    }

    override fun dispatch(action: ChatsAction) {
        when (action) {
            is ChatsAction.FileScan -> {
                viewModelScope.launch {
                    environment.fileScan(action.localFilesDir)
                }
            }
            is ChatsAction.AnalyzeChat -> {
                viewModelScope.launch {
                    environment.analyzeChat(action.chat)
                }
            }
        }
    }

}