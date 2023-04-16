package me.hyuck.kakaoanalyzer.features.detail.message.ui

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import me.hyuck.kakaoanalyzer.features.base.StatefulViewModel
import me.hyuck.kakaoanalyzer.features.detail.message.data.IMessageEnvironment
import me.hyuck.kakaoanalyzer.features.detail.message.data.MessageEnvironment
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	messageEnvironment: MessageEnvironment
) : StatefulViewModel<MessageState, Unit, Unit, IMessageEnvironment>(MessageState(), messageEnvironment) {

	override fun dispatch(action: Unit) {
		TODO("Not yet implemented")
	}

}