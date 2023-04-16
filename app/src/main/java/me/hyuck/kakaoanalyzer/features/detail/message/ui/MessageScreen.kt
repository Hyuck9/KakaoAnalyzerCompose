package me.hyuck.kakaoanalyzer.features.detail.message.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MessageScreen(
	viewModel: MessageViewModel = hiltViewModel(),
	upPress: () -> Unit
) {

	val state by viewModel.state.collectAsStateWithLifecycle()


}