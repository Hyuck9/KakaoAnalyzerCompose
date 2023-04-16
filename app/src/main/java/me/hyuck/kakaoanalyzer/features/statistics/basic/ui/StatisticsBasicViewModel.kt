package me.hyuck.kakaoanalyzer.features.statistics.basic.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import me.hyuck.kakaoanalyzer.features.base.StatefulViewModel
import me.hyuck.kakaoanalyzer.features.statistics.basic.data.IStatisticsBasicEnvironment
import me.hyuck.kakaoanalyzer.features.statistics.basic.data.StatisticsBasicEnvironment
import me.hyuck.kakaoanalyzer.model.Chat
import javax.inject.Inject

@HiltViewModel
class StatisticsBasicViewModel @Inject constructor(
	statisticsBasicEnvironment: StatisticsBasicEnvironment
) : StatefulViewModel<StatisticsBasicState, Unit, Unit, IStatisticsBasicEnvironment>(StatisticsBasicState(), statisticsBasicEnvironment) {

	fun initChat(chat: Chat) {
		setState { copy(chat = chat) }
		initCount()
	}

	private fun initCount() {
		viewModelScope.launch {
			combine(
				environment.getUserCount(state.value.chat),
				environment.getMessageCount(state.value.chat),
				environment.getKeywordCount(state.value.chat)
			) { userCount, messageCount, keywordCount ->
				state.value.copy(userCount = userCount, messageCount = messageCount, keywordCount = keywordCount)
			}.collect {
				setState { it }
			}

		}
	}

	override fun dispatch(action: Unit) {

	}

}