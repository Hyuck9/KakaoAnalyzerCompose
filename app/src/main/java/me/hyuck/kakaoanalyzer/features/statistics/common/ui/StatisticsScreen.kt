package me.hyuck.kakaoanalyzer.features.statistics.common.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import me.hyuck.kakaoanalyzer.features.statistics.basic.ui.StatisticsBasicScreen
import me.hyuck.kakaoanalyzer.features.statistics.keyword.ui.KeywordScreen
import me.hyuck.kakaoanalyzer.features.statistics.participant.ui.ParticipantScreen
import me.hyuck.kakaoanalyzer.features.statistics.time.ui.TimeZoneScreen
import me.hyuck.kakaoanalyzer.foundation.uicomponent.DatePickerButton
import me.hyuck.kakaoanalyzer.foundation.uicomponent.StatisticScaffold
import me.hyuck.kakaoanalyzer.foundation.uicomponent.StatisticsBackHeader
import me.hyuck.kakaoanalyzer.foundation.uicomponent.StatisticsDatePicker
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.StatisticsTab
import java.time.LocalDateTime

@Composable
fun StatisticsScreen(
    viewModel: StatisticsViewModel = hiltViewModel(),
    onDetailParticipants: (String) -> Unit = {},
    onDetailKeywords: (String) -> Unit = {},
    upPress: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var isShowingStartDatePicker by rememberSaveable { mutableStateOf(false) }
    var isShowingEndDatePicker by rememberSaveable { mutableStateOf(false) }

    StatisticScaffold(
        topBar = {
            Surface {
                StatisticsBackHeader(
                    onClickBack = upPress,
                    onClickShare = {}
                )
            }
        }
    ) {
        TabbedViewPagerContent(
            modifier = Modifier.padding(it),
            chat = state.chat,
            tabs = state.statisticsTabs,
            onStartDateClick = { isShowingStartDatePicker = true },
            onEndDateClick = { isShowingEndDatePicker = true },
            onDetailParticipants = { onDetailParticipants(state.chat.id) },
            onDetailKeywords = { onDetailKeywords(state.chat.id) }
        )

        StatisticsDatePicker(
            date = state.chat.startDate,
            visible = isShowingStartDatePicker,
            onConfirm = { startDate ->
                viewModel.dispatch(StatisticsAction.ChangeStartDate(startDate))
            },
            onDismiss = { isShowingStartDatePicker = false }
        )
        StatisticsDatePicker(
            date = state.chat.endDate,
            visible = isShowingEndDatePicker,
            onConfirm = { endDate ->
                viewModel.dispatch(StatisticsAction.ChangeEndDate(endDate))
            },
            onDismiss = { isShowingEndDatePicker = false }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabbedViewPagerContent(
    modifier: Modifier = Modifier,
    chat: Chat,
    tabs: List<StatisticsTab>,
    onStartDateClick: () -> Unit,
    onEndDateClick: () -> Unit,
    onDetailParticipants: () -> Unit = {},
    onDetailKeywords: () -> Unit = {},
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
    ) {
        Surface {
            StatisticsSelector(
                chat = chat,
                onStartDateClick = onStartDateClick,
                onEndDateClick = onEndDateClick
            )
        }
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                )
            }
        ) {
            tabs.forEachIndexed { index, statisticsTab ->
                Tab(
                    selected = index == pagerState.currentPage,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = statisticsTab.value
                        )
                    }
                )
            }
        }
        HorizontalPager(
            pageCount = tabs.count(),
            state = pagerState
        ) { page ->
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                when(tabs[page]) {
                    StatisticsTab.BASIC -> {
                        StatisticsBasicScreen(chat = chat)
                    }
                    StatisticsTab.PARTICIPANT -> {
                        ParticipantScreen(
                            chat = chat,
                            onMoreButtonClick = onDetailParticipants
                        )
                    }
                    StatisticsTab.KEYWORD -> {
                        KeywordScreen(
                            chat = chat,
                            onMoreButtonClick = onDetailKeywords
                        )
                    }
                    StatisticsTab.TIME -> {
                        TimeZoneScreen(chat = chat)
                    }
                }
            }
        }
    }
}

@Composable
fun StatisticsSelector(
    chat: Chat,
    onStartDateClick: () -> Unit,
    onEndDateClick: () -> Unit,
) {
    Column {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = chat.title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DatePickerButton(
                modifier = Modifier.weight(1f),
                text = chat.startDateString,
                onClick = onStartDateClick
            )
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "~"
            )
            DatePickerButton(
                modifier = Modifier.weight(1f),
                text = chat.endDateString,
                onClick = onEndDateClick
            )
        }
    }
}







@Preview
@Composable
private fun StatisticsSelectorPreview() {
    Column {
        StatisticsSelector(
            chat = Chat(
                title = "아빠 님과 카카오톡 대화",
                startDate = LocalDateTime.now(),
                endDate = LocalDateTime.now(),
            ),
            onStartDateClick = {},
            onEndDateClick = {}
        )
    }
}

@Preview
@Composable
private fun ViewPagerWithTabPreview() {
    TabbedViewPagerContent(
        chat = Chat(
            title = "아빠 님과 카카오톡 대화",
            startDate = LocalDateTime.now(),
            endDate = LocalDateTime.now(),
        ),
        tabs = StatisticsTab.values().asList(),
        onStartDateClick = {},
        onEndDateClick = {}
    )
}