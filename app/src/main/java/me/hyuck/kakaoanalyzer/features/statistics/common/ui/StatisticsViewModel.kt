package me.hyuck.kakaoanalyzer.features.statistics.common.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import me.hyuck.kakaoanalyzer.features.base.StatefulViewModel
import me.hyuck.kakaoanalyzer.model.StatisticsTab
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(

) : StatefulViewModel<StatisticsState, Unit, Unit, Unit>(StatisticsState(), Unit) {

	private val selectedTab = MutableStateFlow(StatisticsTab.BASIC)
	private val tabs = MutableStateFlow(StatisticsTab.values().asList())

	init {
		viewModelScope.launch {
			combine(
				tabs,
				selectedTab
			) { tabs, selectedTab ->
				StatisticsState(
					statisticsTabs = tabs,
					selectedTab = selectedTab
				)
			}.catch { throwable ->
				throw throwable
			}.collect {
				setState { it }
			}
		}
	}

	override fun dispatch(action: Unit) {

	}
}