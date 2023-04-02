package me.hyuck.kakaoanalyzer.features.detail.ui

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import me.hyuck.kakaoanalyzer.features.base.StatefulViewModel
import me.hyuck.kakaoanalyzer.features.detail.data.DetailEnvironment
import me.hyuck.kakaoanalyzer.features.detail.data.IDetailEnvironment
import me.hyuck.kakaoanalyzer.foundation.extension.testLog
import me.hyuck.kakaoanalyzer.runtime.MainDestinations
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	detailEnvironment: DetailEnvironment
) : StatefulViewModel<DetailState, Unit, Unit, IDetailEnvironment>(DetailState(), detailEnvironment) {

	private val chatId = savedStateHandle.get<String>(MainDestinations.CHAT_ID_KEY)

	init {
		testLog("chatId : $chatId")
	}
	override fun dispatch(action: Unit) {
	}

}