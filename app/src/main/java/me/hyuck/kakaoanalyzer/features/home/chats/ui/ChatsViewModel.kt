package me.hyuck.kakaoanalyzer.features.home.chats.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.hyuck.kakaoanalyzer.features.base.StatefulViewModel
import me.hyuck.kakaoanalyzer.features.home.chats.data.IChatsEnvironment
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    chatsEnvironment: IChatsEnvironment
) : StatefulViewModel<ChatsState, Unit, ChatsAction, IChatsEnvironment>(ChatsState(), chatsEnvironment) {

    override fun dispatch(action: ChatsAction) {
        when (action) {
            is ChatsAction.FileScan -> {
                viewModelScope.launch {
                    environment.fileScan(action.localFilesDir)
                }
            }
        }
    }

}