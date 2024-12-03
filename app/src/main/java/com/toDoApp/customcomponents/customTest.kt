package com.toDoApp.customcomponents

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.toDoApp.R


@Composable
fun CustomText(
    text: String,
    modifier: Modifier = Modifier.wrapContentSize(),
    fontSize: Float = 16f,
    textAlign: TextAlign = TextAlign.Start,
    textStyle: TextStyle = TextStyle.Default,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    isSingleLine: Boolean = false,
    fontFamily: Font = Font(R.font.roboto_regular)
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = fontSize.sp,
        textAlign = textAlign,
        style = textStyle,
        overflow = overflow,
        fontFamily = FontFamily(fontFamily),
        maxLines = if (isSingleLine) 1 else Int.MAX_VALUE,
    )
}