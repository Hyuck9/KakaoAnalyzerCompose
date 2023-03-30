package me.hyuck.kakaoanalyzer.features.statistics.participant.ui

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.mikephil.charting.formatter.PercentFormatter
import me.hyuck.kakaoanalyzer.R
import me.hyuck.kakaoanalyzer.foundation.uicomponent.OneOnOneDialog
import me.hyuck.kakaoanalyzer.foundation.uicomponent.ParticipantItemCell
import me.hyuck.kakaoanalyzer.foundation.uicomponent.SimpleTextButton
import me.hyuck.kakaoanalyzer.foundation.uicomponent.rememberPieChart
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Participant
import me.hyuck.kakaoanalyzer.model.mapper.toPieData
import me.hyuck.kakaoanalyzer.model.mapper.toPieEntries
import splitties.resources.appStr

@Composable
fun ParticipantScreen(
	viewModel: ParticipantViewModel = hiltViewModel(),
	chat: Chat,
) {
	viewModel.initChat(chat)
	val state by viewModel.state.collectAsStateWithLifecycle()

	ParticipantContent(participants = state.items, isOneOnOne = state.isOneOnOne)
}

@Composable
private fun ParticipantContent(
	modifier: Modifier = Modifier,
	participants: List<Participant>,
	isOneOnOne: Boolean = false
) {
	var isShowingDialog by rememberSaveable { mutableStateOf(false) }
	LazyColumn(
		modifier = modifier
			.fillMaxSize()
			.padding(horizontal = 16.dp)
	) {
		item {
			PieChart(
				modifier = Modifier.padding(top = 16.dp),
				participants = participants
			)
		}
		items(
			items = participants,
			key = { item -> item.userName }
		) { participant ->
			ParticipantItemCell(participant = participant)
		}
		if (participants.size == 10) {
			item {
				SimpleTextButton(text = stringResource(R.string.button_more))
			}
		} else if (isOneOnOne) {
			item {
				SimpleTextButton(
					text = stringResource(R.string.button_one_on_one),
					onClick = { isShowingDialog = true }
				)
			}
		}
	}

	if (isShowingDialog) {
		OneOnOneDialog(
			setShowDialog = { isShowingDialog = false }
		)
	}
}

@Composable
private fun PieChart(
	modifier: Modifier = Modifier,
	participants: List<Participant>
) {
	val pieChart = rememberPieChart()

	LaunchedEffect(participants) {
		val pieData = participants
			.toPieEntries()
			.toPieData(appStr(R.string.label_chart_participant)).apply {
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