package me.hyuck.kakaoanalyzer.features.detail.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.hyuck.kakaoanalyzer.foundation.extension.testLog
import me.hyuck.kakaoanalyzer.foundation.uicomponent.StatisticScaffold
import me.hyuck.kakaoanalyzer.foundation.uicomponent.StatisticsBackHeader
import me.hyuck.kakaoanalyzer.model.DetailType

@Composable
fun DetailScreen(
	viewModel: DetailViewModel = hiltViewModel(),
	detailType: DetailType,
	upPress: () -> Unit
) {

	testLog("detailType : $detailType")

	val state by viewModel.state.collectAsStateWithLifecycle()

	StatisticScaffold(
		topBar = {
			Surface {
				StatisticsBackHeader(
					titleResId = detailType.titleResId,
					onClickBack = upPress,
					onClickShare = {}
				)
			}
		}
	) {

	}
}