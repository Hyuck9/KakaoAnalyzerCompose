package me.hyuck.kakaoanalyzer.foundation.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.hyuck.kakaoanalyzer.R
import me.hyuck.kakaoanalyzer.foundation.theme.AlphaDisabled
import me.hyuck.kakaoanalyzer.foundation.theme.KakaoAnalyzerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KakaoAnalyzerTextField(
	modifier: Modifier = Modifier,
	value: TextFieldValue,
	onValueChange: (TextFieldValue) -> Unit,
	placeholderValue: String,
	trailingIcon: @Composable (() -> Unit)? = null,
	leadingIcon: @Composable (() -> Unit)? = null,
	isError: Boolean = false,
	visualTransformation: VisualTransformation = VisualTransformation.None,
	keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(capitalization = KeyboardCapitalization.Sentences),
	keyboardActions: KeyboardActions = KeyboardActions.Default,
	shape: Shape = MaterialTheme.shapes.small,
	textColor: Color = MaterialTheme.colorScheme.onSurface,
	textStyle: TextStyle = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.onSurface),
	singleLine: Boolean = false,
) {
	OutlinedTextField(
		modifier = modifier
			.background(
				color = MaterialTheme.colorScheme.secondary,
				shape = shape
			),
		value = value,
		onValueChange = { onValueChange(it) },
		placeholder = {
			Text(
				text = placeholderValue,
				style = textStyle,
				color = MaterialTheme.colorScheme.onSurface.copy(AlphaDisabled)
			)
		},
		visualTransformation = visualTransformation,
		trailingIcon = trailingIcon,
		leadingIcon = leadingIcon,
		textStyle = textStyle,
		isError = isError,
		keyboardOptions = keyboardOptions,
		keyboardActions = keyboardActions,
		colors = TextFieldDefaults.outlinedTextFieldColors(
			focusedBorderColor = Color.Transparent,
			unfocusedBorderColor = Color.Transparent,
			textColor = textColor
		),
		singleLine = singleLine
	)
}

@Composable
fun SearchBar(
	modifier: Modifier = Modifier,
	query: TextFieldValue = TextFieldValue(""),
	placeholder: String = stringResource(id = R.string.search_keyword),
	onQueryChange: (TextFieldValue) -> Unit = {},
	onClearQuery: () -> Unit = {}
) {
	KakaoAnalyzerModalRow(
		modifier = modifier.padding(all = 16.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		KakaoAnalyzerTextField(
			modifier = modifier
				.heightIn(min = 50.dp, max = 200.dp)
				.weight(0.6f),
			value = query,
			placeholderValue = placeholder,
			onValueChange = onQueryChange,
			shape = MaterialTheme.shapes.large,
			keyboardOptions = KeyboardOptions.Default.copy(
				imeAction = ImeAction.None,
				capitalization = KeyboardCapitalization.Sentences
			),
			singleLine = true,
			leadingIcon = {
				Icon(
					imageVector = Icons.Rounded.Search,
					tint = LocalContentColor.current,
					contentDescription = ""
				)
			},
			trailingIcon = {
				if (query.text.isNotEmpty()) {
					KakaoAnalyzerIconButton(
						imageVector = Icons.Rounded.Clear,
						onClick = onClearQuery
					)
				}
			}
		)
	}
}




@Preview(showBackground = true)
@Composable
private fun KakaoAnalyzerTextFieldPreview() {
	KakaoAnalyzerTextField(
		modifier = Modifier.height(50.dp),
		value = TextFieldValue(),
		onValueChange = {},
		placeholderValue = "키워드 검색",
		shape = MaterialTheme.shapes.large,
		textStyle = MaterialTheme.typography.titleSmall,
		leadingIcon = {
			Icon(
				imageVector = Icons.Rounded.Search,
				tint = LocalContentColor.current,
				contentDescription = ""
			)
		}
	)
}

@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
	KakaoAnalyzerTheme {
		SearchBar()
	}
}