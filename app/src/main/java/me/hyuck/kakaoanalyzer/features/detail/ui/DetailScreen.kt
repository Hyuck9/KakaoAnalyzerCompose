package me.hyuck.kakaoanalyzer.features.detail.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.hyuck.kakaoanalyzer.R
import me.hyuck.kakaoanalyzer.foundation.extension.testLog
import me.hyuck.kakaoanalyzer.foundation.theme.KakaoAnalyzerTheme
import me.hyuck.kakaoanalyzer.foundation.uicomponent.KakaoAnalyzerSurface
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

@Composable
private fun SearchBar(
	modifier: Modifier = Modifier,
	query: TextFieldValue = TextFieldValue(""),
	onQueryChange: (TextFieldValue) -> Unit = {},
	onClearQuery: () -> Unit = {}
) {
	KakaoAnalyzerSurface(
		shape = MaterialTheme.shapes.small,
		modifier = modifier
			.fillMaxWidth()
			.height(56.dp)
			.padding(horizontal = 24.dp, vertical = 8.dp)
	) {
		Box(Modifier.fillMaxSize()) {
			if (query.text.isEmpty()) {
				SearchHint()
			}
			Row(
				verticalAlignment = Alignment.CenterVertically,
				modifier = Modifier
					.fillMaxSize()
					.wrapContentHeight()
			) {
				BasicTextField(
					value = query,
					onValueChange = onQueryChange,
					modifier = Modifier
						.weight(1f)
				)
				if (query.text.isNotEmpty()) {
					IconButton(onClick = onClearQuery) {
						Icon(
							imageVector = Icons.Outlined.Clear,
							contentDescription = stringResource(id = R.string.label_clear)
						)
					}
				}
			}
		}
	}
}

@Composable
private fun SearchHint() {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.fillMaxSize()
			.wrapContentSize()
	) {
		Icon(
			imageVector = Icons.Outlined.Search,
			contentDescription = stringResource(id = R.string.label_search)
		)
		Spacer(modifier = Modifier.width(8.dp))
		Text(
			text = stringResource(id = R.string.search_keyword)
		)
	}
}


@Preview
@Composable
private fun SearchBarPreview() {
	KakaoAnalyzerTheme {
		KakaoAnalyzerSurface {
			SearchBar(
			)
		}
	}
}