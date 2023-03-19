package me.hyuck.kakaoanalyzer.features.statistics.keyword.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.hyuck.kakaoanalyzer.foundation.uicomponent.ContentRow
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Keyword

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
		items(
			items = keywords,
			key = { item -> item.word }
		) { keyword ->
			ContentRow(title = keyword.word, count = keyword.wordCount)
		}
	}

}
