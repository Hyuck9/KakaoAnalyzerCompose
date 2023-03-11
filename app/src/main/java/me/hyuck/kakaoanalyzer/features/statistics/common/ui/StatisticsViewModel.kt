package me.hyuck.kakaoanalyzer.features.statistics.common.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import me.hyuck.kakaoanalyzer.features.base.StatefulViewModel
import me.hyuck.kakaoanalyzer.features.statistics.common.data.IStatisticsEnvironment
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
	statisticsEnvironment: IStatisticsEnvironment
) : StatefulViewModel<StatisticsState, Unit, Unit, IStatisticsEnvironment>(StatisticsState(), statisticsEnvironment) {

	override fun dispatch(action: Unit) {

	}

}