package me.hyuck.kakaoanalyzer.foundation.uicomponent

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeDismiss(
	modifier: Modifier = Modifier,
	background: @Composable (direction: DismissDirection, fraction: Float) -> Unit,
	content: @Composable (isDismissed: Boolean) -> Unit,
	directions: Set<DismissDirection> = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
	enter: EnterTransition = expandVertically(),
	exit: ExitTransition = shrinkVertically(
		animationSpec = tween(
			durationMillis = 400,
		)
	),
	dismissState: DismissState,
	onDismiss: () -> Unit,
	onComplete: () -> Unit
) {
}