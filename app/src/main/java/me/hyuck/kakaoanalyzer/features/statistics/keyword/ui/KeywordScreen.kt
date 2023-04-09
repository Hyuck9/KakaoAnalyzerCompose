package me.hyuck.kakaoanalyzer.features.statistics.keyword.ui

import android.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.mikephil.charting.formatter.PercentFormatter
import me.hyuck.kakaoanalyzer.R
import me.hyuck.kakaoanalyzer.foundation.uicomponent.ContentRow
import me.hyuck.kakaoanalyzer.foundation.uicomponent.FilterBar
import me.hyuck.kakaoanalyzer.foundation.uicomponent.SimpleTextButton
import me.hyuck.kakaoanalyzer.foundation.uicomponent.rememberPieChart
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Keyword
import me.hyuck.kakaoanalyzer.model.mapper.toPieData
import me.hyuck.kakaoanalyzer.model.mapper.toPieEntries
import splitties.resources.appStr

@Composable
fun KeywordScreen(
	viewModel: KeywordViewModel = hiltViewModel(),
	chat: Chat,
	onMoreButtonClick: () -> Unit = {}
) {
	viewModel.initChat(chat)

	LaunchedEffect(chat) {
		viewModel.initFilters()
	}
	val state by viewModel.state.collectAsStateWithLifecycle()

	Column {
		FilterBar(filters = state.filters)
		KeywordContent(
			keywords = state.items,
			onMoreButtonClick = onMoreButtonClick
		)
	}

}

@Composable
fun KeywordContent(
	modifier: Modifier = Modifier,
	keywords: List<Keyword>,
	onMoreButtonClick: () -> Unit = {}
) {
	LazyColumn(
		modifier = modifier
			.fillMaxSize()
			.padding(horizontal = 16.dp)
	) {
		item {
			PieChart(
				modifier = Modifier.padding(top = 16.dp),
				keywords = keywords
			)
		}
		items(
			items = keywords,
			key = { item -> item.word }
		) { keyword ->
			ContentRow(title = keyword.word, count = keyword.wordCount)
		}
		if (keywords.size == 10) {
			item {
				SimpleTextButton(
					text = stringResource(R.string.button_more),
					onClick = onMoreButtonClick
				)
			}
		}
	}

}

@Composable
private fun PieChart(
	modifier: Modifier = Modifier,
	keywords: List<Keyword>
) {
	val pieChart = rememberPieChart()

	LaunchedEffect(keywords) {
		val pieData = keywords
			.toPieEntries()
			.toPieData(appStr(R.string.label_chart_keyword)).apply {
				setValueFormatter(PercentFormatter(pieChart))
				setValueTextSize(11f)
				setValueTextColor(Color.WHITE)
			}
		pieChart.data = pieData
		pieChart.highlightValues(null)
		pieChart.invalidate()
	}

	AndroidView(
		modifier = modifier
			.fillMaxWidth()
			.height(350.dp)
			.padding(16.dp),
		factory = {
			pieChart
		}
	)
}