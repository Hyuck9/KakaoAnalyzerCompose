package me.hyuck.kakaoanalyzer.features.statistics.keyword.ui

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.mikephil.charting.formatter.PercentFormatter
import me.hyuck.kakaoanalyzer.R
import me.hyuck.kakaoanalyzer.foundation.uicomponent.ContentRow
import me.hyuck.kakaoanalyzer.foundation.uicomponent.rememberPieChart
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Keyword
import me.hyuck.kakaoanalyzer.model.mapper.toPieData
import me.hyuck.kakaoanalyzer.model.mapper.toPieEntries
import splitties.resources.appStr

@Composable
fun KeywordScreen(
	viewModel: KeywordViewModel = hiltViewModel(),
	chat: Chat
) {
	viewModel.initChat(chat)
	val state by viewModel.state.collectAsStateWithLifecycle()

	KeywordContent(keywords = state.items)
}

@Composable
fun KeywordContent(
	modifier: Modifier = Modifier,
	keywords: List<Keyword>
) {
	LazyColumn(
		modifier = modifier
			.fillMaxSize()
			.padding(all = 16.dp)
	) {
		item {
			PieChart(keywords = keywords)
		}
		items(
			items = keywords,
			key = { item -> item.word }
		) { keyword ->
			ContentRow(title = keyword.word, count = keyword.wordCount)
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