package me.hyuck.kakaoanalyzer.features.statistics.keyword.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.hyuck.kakaoanalyzer.features.base.StatefulViewModel
import me.hyuck.kakaoanalyzer.features.statistics.keyword.data.IKeywordEnvironment
import me.hyuck.kakaoanalyzer.features.statistics.keyword.data.KeywordEnvironment
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Filter
import javax.inject.Inject

@HiltViewModel
class KeywordViewModel @Inject constructor(
	keywordEnvironment: KeywordEnvironment
) : StatefulViewModel<KeywordState, Unit, Unit, IKeywordEnvironment>(KeywordState(), keywordEnvironment) {

	fun initChat(chat: Chat) {
		setState { copy(chat = chat) }
		initKeywords()
	}

	fun initFilters() {
		viewModelScope.launch {
			environment.getUsers(state.value.chat)
				.collect { userName ->
					val filters = userName.map { Filter(name = it) }.toMutableList()
					filters.add(0, Filter(name = "전체"))
					setState { copy(filters = filters) }
				}
		}
	}

	private fun initKeywords() {
		viewModelScope.launch {
			environment.getKeywords(state.value.chat, 10)
				.collect {
					setState { copy(items = it) }
				}
		}
	}

	override fun dispatch(action: Unit) {
	}
}