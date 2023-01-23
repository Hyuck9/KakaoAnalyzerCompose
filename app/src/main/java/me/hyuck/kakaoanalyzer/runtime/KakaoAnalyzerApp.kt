package me.hyuck.kakaoanalyzer.runtime

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.hyuck.kakaoanalyzer.foundation.theme.KakaoAnalyzerTheme
import me.hyuck.kakaoanalyzer.foundation.uicomponent.KakaoAnalyzerBottomBar
import me.hyuck.kakaoanalyzer.foundation.uicomponent.KakaoAnalyzerScaffold

@Composable
fun KakaoAnalyzerApp() {
    KakaoAnalyzerTheme {
        val appState = rememberKakaoAnalyzerAppState()
        KakaoAnalyzerScaffold(
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    KakaoAnalyzerBottomBar(
                        tabs = appState.bottomBarTabs,
                        currentRoute = appState.currentRoute!!,
                        navigateToRoute = appState::navigateToBottomBarRoute
                    )
                }
            },
            snackbarHost = {
                SnackbarHost(
                    hostState = appState.snackbarHostState,
                    modifier = Modifier.systemBarsPadding(),
                    snackbar = { snackbarData -> }
                )
            }
        ) { innerPaddingModifier ->

        }
    }
}