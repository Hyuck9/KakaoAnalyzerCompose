package me.hyuck.kakaoanalyzer.foundation.uicomponent

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KakaoAnalyzerScaffold(
    modifier: Modifier = Modifier,
    bottomBar: @Composable (() -> Unit) = {},
    snackbarHost: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        content = content
    )
}