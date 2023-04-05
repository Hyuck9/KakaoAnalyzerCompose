package me.hyuck.kakaoanalyzer.features.detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.hyuck.kakaoanalyzer.foundation.extension.testLog
import me.hyuck.kakaoanalyzer.foundation.uicomponent.ContentRow
import me.hyuck.kakaoanalyzer.foundation.uicomponent.SearchBar
import me.hyuck.kakaoanalyzer.foundation.uicomponent.StatisticScaffold
import me.hyuck.kakaoanalyzer.foundation.uicomponent.StatisticsBackHeader
import me.hyuck.kakaoanalyzer.model.DetailType
import me.hyuck.kakaoanalyzer.model.Keyword

@Composable
fun DetailScreen(
	viewModel: DetailViewModel = hiltViewModel(),
	detailType: DetailType = DetailType.KEYWORD,
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
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(it)
		) {
			SearchBar()
			DetailContent()
		}
	}
}

@Composable
fun DetailContent(
	modifier: Modifier = Modifier,
	keywords: List<Keyword> = emptyList(),
) {
	LazyColumn(
		modifier = modifier
			.fillMaxSize()
			.padding(horizontal = 16.dp)
	) {
		items(
			items = keywords,
			key = { item -> item.word }
		) { keyword ->
			ContentRow(title = keyword.word, count = keyword.wordCount)
		}
	}
}