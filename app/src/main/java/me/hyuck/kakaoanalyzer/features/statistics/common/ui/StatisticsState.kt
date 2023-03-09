package me.hyuck.kakaoanalyzer.features.statistics.common.ui

import androidx.compose.runtime.Immutable
import me.hyuck.kakaoanalyzer.model.StatisticsTab

@Immutable
data class StatisticsState(
	val statisticsTabs: List<StatisticsTab> =  StatisticsTab.values().asList()
)