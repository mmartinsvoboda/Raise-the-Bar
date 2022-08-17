/*
 * Created by  Martin Svoboda
 * Copyright (c) IS4U s.r.o. 2021. All rights reserved.
 * Last modified 03.06.22 9:43
 */

package com.mmartinsvoboda.sporttrackingapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme

@Composable
fun DisplayErrorTextOnError(error: String?) {
    if (!error.isNullOrBlank()) {
        Column {
            SpacerTiny()

            Text(
                text = error,
                color = SportTrackingAppTheme.colors.error
            )
        }
    }
}

@Composable
fun ButtonText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = FontWeight.Medium,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    inlineContent: Map<String, InlineTextContent> = mapOf(),
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = SportTrackingAppTheme.typography.body1
) {
    Text(
        AnnotatedString.Builder(text.uppercase()).toAnnotatedString(),
        modifier,
        color,
        fontSize,
        fontStyle,
        fontWeight,
        fontFamily,
        letterSpacing,
        textDecoration,
        TextAlign.Center,
        lineHeight,
        overflow,
        softWrap,
        maxLines,
        inlineContent,
        onTextLayout,
        style
    )
}

@Composable
fun TopBarText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = FontWeight.Bold,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = false,
    maxLines: Int = 1,
    inlineContent: Map<String, InlineTextContent> = mapOf(),
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) {
    Text(
        AnnotatedString.Builder(text).toAnnotatedString(),
        modifier,
        color,
        fontSize,
        fontStyle,
        fontWeight,
        fontFamily,
        letterSpacing,
        textDecoration,
        TextAlign.Start,
        lineHeight,
        overflow,
        softWrap,
        maxLines,
        inlineContent,
        onTextLayout,
        style,
    )
}

@Composable
fun IconAndTextRow(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current,
    icon: ImageVector,
    iconColor: Color? = null
) {
    Column(modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                icon,
                contentDescription = text,
                modifier = Modifier.requiredSize(18.dp),
                tint = iconColor
                    ?: LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
            )

            SpacerDefault()

            Text(
                text = text,
                Modifier,
                color,
                fontSize,
                fontStyle,
                fontWeight,
                fontFamily,
                letterSpacing,
                textDecoration,
                textAlign,
                lineHeight,
                overflow,
                softWrap,
                maxLines,
                onTextLayout,
                style
            )
        }
    }
}

@Composable
fun TitleAndTextColumn(
    title: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = title,
            style = SportTrackingAppTheme.typography.caption,
            fontWeight = FontWeight.Bold
        )
        SpacerTiny()
        Text(text = text)
    }
}