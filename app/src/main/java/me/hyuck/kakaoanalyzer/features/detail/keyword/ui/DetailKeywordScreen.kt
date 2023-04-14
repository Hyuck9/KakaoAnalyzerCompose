package me.hyuck.kakaoanalyzer.features.detail.keyword.ui

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
import me.hyuck.kakaoanalyzer.R
import me.hyuck.kakaoanalyzer.foundation.uicomponent.*
import me.hyuck.kakaoanalyzer.model.Keyword

@Composable
fun DetailKeywordScreen(
    viewModel: DetailKeywordViewModel = hiltViewModel(),
    upPress: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    viewModel.dispatch(DetailKeywordAction.ObserveKeywords(state.filters))

    StatisticScaffold(
        topBar = {
            Surface {
                StatisticsBackHeader(
                    titleResId = R.string.title_detail_keyword,
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
            SearchBar(
                query = state.query,
                onQueryChange = { /* TODO: query Change Action */ },
                onClearQuery = { /* TODO: clear Action*/ }
            )
            FilterBar(filters = state.filters)
            DetailContent(
                keywords = state.items
            )
        }
    }
}

@Composable
private fun DetailContent(
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