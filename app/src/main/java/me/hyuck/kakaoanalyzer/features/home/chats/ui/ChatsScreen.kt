package me.hyuck.kakaoanalyzer.features.home.chats.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.hyuck.kakaoanalyzer.R
import me.hyuck.kakaoanalyzer.foundation.preview.BooleanParameterProvider
import me.hyuck.kakaoanalyzer.foundation.theme.KakaoAnalyzerTheme
import me.hyuck.kakaoanalyzer.foundation.theme.KakaoYellow
import me.hyuck.kakaoanalyzer.foundation.uicomponent.ProgressChatItemCell
import timber.log.Timber


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatsScreen(
	viewModel: ChatsViewModel
) {
	val localFilesDir = LocalContext.current.filesDir
	val state by viewModel.state.collectAsStateWithLifecycle()

	var testProgress by remember { mutableStateOf(60f) }

	LaunchedEffect(Unit) {
		viewModel.dispatch(ChatsAction.FileScan(localFilesDir))
		Timber.tag("TEST").i("LaunchedEffect")
	}

	Scaffold(
		floatingActionButton = {
			GoToKakaoTalkButton(
				extended = true,
			)
		}
	) { padding ->

		ProgressChatItemCell(
			modifier = Modifier.padding(padding),
			percent = testProgress
		)
	}

}

@Composable
private fun ChatsContent(
	modifier: Modifier = Modifier,
	chats: List<ChatItem>
) {
	LazyColumn(
		modifier = modifier
			.fillMaxSize()
	) {
		items(
			items = chats,
			key = { item -> item.identifier() }
		) { item ->
			when (item) {
				is ChatItem.New -> {
					item.chat
				}
				is ChatItem.InProgress -> {}
				is ChatItem.Complete -> {}
			}
		}
	}
}

@Composable
private fun GoToKakaoTalkButton(
	extended: Boolean,
) {
	val context = LocalContext.current
	FloatingActionButton(
		onClick = {
			with(context) {
				startActivity(
					packageManager.getLaunchIntentForPackage("com.kakao.talk")
				)
			}
		},
		containerColor = KakaoYellow
	) {
		Row(
			modifier = Modifier.padding(horizontal = 8.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			Image(
				painter = painterResource(id = R.drawable.kakaotalk),
				contentDescription = stringResource(id = R.string.fab_kakao_talk)
			)

			AnimatedVisibility(visible = extended) {
				Text(
					text = stringResource(id = R.string.fab_kakao_talk),
					modifier = Modifier.padding(horizontal = 8.dp)
				)
			}
		}
	}
}



@Preview(showBackground = true)
@Composable
private fun GoToKakaoTalkButtonPreview(@PreviewParameter(BooleanParameterProvider::class) extended: Boolean) {
	KakaoAnalyzerTheme {
		GoToKakaoTalkButton(
			extended = extended
		)
	}
}