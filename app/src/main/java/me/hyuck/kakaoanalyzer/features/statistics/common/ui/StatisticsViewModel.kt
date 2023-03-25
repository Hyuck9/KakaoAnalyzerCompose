package me.hyuck.kakaoanalyzer.features.statistics.common.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.hyuck.kakaoanalyzer.features.base.StatefulViewModel
import me.hyuck.kakaoanalyzer.features.statistics.common.data.IStatisticsEnvironment
import me.hyuck.kakaoanalyzer.runtime.MainDestinations
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	statisticsEnvironment: IStatisticsEnvironment
) : StatefulViewModel<StatisticsState, Unit, StatisticsAction, IStatisticsEnvironment>(StatisticsState(), statisticsEnvironment) {

	private val chatId = savedStateHandle.get<String>(MainDestinations.CHAT_ID_KEY)

	init {
		initChat()
	}

	private fun initChat() {
		viewModelScope.launch {
			if (chatId != null) {
				environment.getChatById(chatId)
					.collect {
						setState { copy(chat = it) }
					}
			}
		}
	}

	override fun dispatch(action: StatisticsAction) {
		when (action) {
			is StatisticsAction.ChangeStartDate -> {
				setState {
					copy(
						chat = chat.copy(startDate = action.startDate)
					)
				}
			}
			is StatisticsAction.ChangeEndDate -> {
				setState {
					copy(
						chat = chat.copy(endDate = action.endDate)
					)
				}
			}
		}
	}

}