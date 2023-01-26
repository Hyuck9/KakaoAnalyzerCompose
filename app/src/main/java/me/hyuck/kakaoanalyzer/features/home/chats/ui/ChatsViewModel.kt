package me.hyuck.kakaoanalyzer.features.home.chats.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import me.hyuck.kakaoanalyzer.features.base.StatefulViewModel
import me.hyuck.kakaoanalyzer.features.home.chats.data.IChatsEnvironment
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    chatsEnvironment: IChatsEnvironment
) : StatefulViewModel<ChatsState, Unit, Unit, IChatsEnvironment>(ChatsState(), chatsEnvironment) {

    init {
        environment.test()
    }
    override fun dispatch(action: Unit) {
    }

}