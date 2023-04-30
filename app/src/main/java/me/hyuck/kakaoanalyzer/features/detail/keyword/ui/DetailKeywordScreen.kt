package me.hyuck.kakaoanalyzer.features.detail.keyword.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import me.hyuck.kakaoanalyzer.R
import me.hyuck.kakaoanalyzer.foundation.uicomponent.*
import me.hyuck.kakaoanalyzer.model.Keyword

@Composable
fun DetailKeywordScreen(
    viewModel: DetailKeywordViewModel = hiltViewModel(),
    upPress: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()


    LaunchedEffect(state.query) {
        viewModel.dispatch(DetailKeywordAction.ObserveKeywords(state.filters))
    }

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
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchBar(
                query = state.query,
                onQueryChange = { viewModel.dispatch(DetailKeywordAction.OnQueryChange(it)) },
                onClearQuery = { viewModel.dispatch(DetailKeywordAction.OnClearQuery) }
            )
            FilterBar(filters = state.filters)
            DetailContent(
                keywords = state.items.collectAsLazyPagingItems()
            )
        }
    }
}

@Composable
private fun DetailContent(
    modifier: Modifier = Modifier,
    keywords: LazyPagingItems<Keyword>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(
            items = keywords
        ) { keyword ->
            keyword?.let {
                ContentRow(title = keyword.word, count = keyword.wordCount)
            }
        }
//        items(
//            items = keywords,
//            key = { item -> item.word }
//        ) { keyword ->
//            ContentRow(title = keyword.word, count = keyword.wordCount)
//        }
    }
}