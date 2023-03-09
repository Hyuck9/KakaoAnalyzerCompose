package me.hyuck.kakaoanalyzer.features.statistics.common.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import me.hyuck.kakaoanalyzer.features.base.StatefulViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(

) : StatefulViewModel<StatisticsState, Unit, Unit, Unit>(StatisticsState(), Unit) {

	override fun dispatch(action: Unit) {

	}

}