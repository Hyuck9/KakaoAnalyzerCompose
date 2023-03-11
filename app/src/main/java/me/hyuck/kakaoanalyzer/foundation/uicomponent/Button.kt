package me.hyuck.kakaoanalyzer.foundation.uicomponent

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KakaoAnalyzerIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    imageVector: ImageVector = Icons.Rounded.ArrowBack,
    tint: Color = LocalContentColor.current
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = imageVector,
            tint = tint,
            contentDescription = ""
        )
    }
}

@Composable
fun SelectorButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier.height(32.dp),
        onClick = onClick,
        shape = RoundedCornerShape(2.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}



@Preview
@Composable
private fun SelectorButtonPreview() {
    Surface {
        SelectorButton(
            text = "2023-03-11",
            onClick = {}
        )
    }
}