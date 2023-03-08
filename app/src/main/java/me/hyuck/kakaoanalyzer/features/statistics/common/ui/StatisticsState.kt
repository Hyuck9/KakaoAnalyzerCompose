package me.hyuck.kakaoanalyzer.features.statistics.common.ui

import androidx.compose.runtime.Immutable
import me.hyuck.kakaoanalyzer.model.StatisticsTab

@Immutable
data class StatisticsState(
	val selectedTab: StatisticsTab = StatisticsTab.BASIC,
	val statisticsTabs: List<StatisticsTab> =  emptyList()
)