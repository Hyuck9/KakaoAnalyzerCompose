package me.hyuck.kakaoanalyzer.foundation.uicomponent

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import me.hyuck.kakaoanalyzer.R
import me.hyuck.kakaoanalyzer.foundation.extension.toLocalDateTime
import me.hyuck.kakaoanalyzer.foundation.extension.toMillis
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsDatePicker(
    date: LocalDateTime = LocalDateTime.now(),
    visible: Boolean = true,
    onConfirm: (LocalDateTime) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    if (visible) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = date.toMillis()
        )

        DatePickerDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let {
                            onConfirm(it.toLocalDateTime())
                        }
                        onDismiss()
                    }
                ) {
                    Text(text = stringResource(id = R.string.button_confirm_label))
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = stringResource(id = R.string.button_cancel_label))
                }
            }
        ) {
            DatePicker(datePickerState = datePickerState)
        }
    }
}