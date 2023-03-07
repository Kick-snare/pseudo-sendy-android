package com.uzun.pseudosendy.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.uzun.pseudosendy.presentation._const.UIConst.BUTTON_RADIUS_LARGE
import com.uzun.pseudosendy.presentation._const.UIConst.BUTTON_RADIUS_NORMAL
import com.uzun.pseudosendy.presentation._const.UIConst.BUTTON_RADIUS_SMALL
import com.uzun.pseudosendy.presentation._const.UIConst.BUTTON_WIDTH_LARGE
import com.uzun.pseudosendy.presentation._const.UIConst.BUTTON_WIDTH_NORMAL
import com.uzun.pseudosendy.presentation._const.UIConst.BUTTON_WIDTH_SMALL
import com.uzun.pseudosendy.presentation._const.UIConst.SPACE_M
import com.uzun.pseudosendy.ui.theme.DayBlueBase
import com.uzun.pseudosendy.ui.theme.DayGrayscale400
import com.uzun.pseudosendy.ui.theme.White

enum class ButtonSize(val width: Dp, val radius: Dp, val padding: Dp) {
    NORMAL(BUTTON_WIDTH_NORMAL, BUTTON_RADIUS_NORMAL, SPACE_M),
    SMALL(BUTTON_WIDTH_SMALL, BUTTON_RADIUS_SMALL, 11.dp),
    LARGE(BUTTON_WIDTH_LARGE, BUTTON_RADIUS_LARGE, SPACE_M),
}

@Composable
fun BaseRoundedButton(
    onClick: () -> Unit,
    type: ButtonSize,
    modifier: Modifier,
    colors: ButtonColors,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(type.radius),
        colors = colors,
        modifier = modifier
            .fillMaxWidth(),
//            .width(type.width),
        contentPadding = PaddingValues(type.padding),
        content = content
    )
}

@Composable
fun BaseSquareButton(
    onClick: () -> Unit,
    type: ButtonSize,
    modifier: Modifier,
    colors: ButtonColors,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(0.dp),
        colors = colors,
        modifier = modifier.fillMaxWidth(),
//            .width(type.width)
        contentPadding = PaddingValues(type.padding),
        content = content
    )
}

@Composable
fun RoundedPrimaryButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    type: ButtonSize = ButtonSize.NORMAL,
    content: @Composable RowScope.() -> Unit = {},
) {
    BaseRoundedButton(
        onClick = onClick,
        type = type,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = DayBlueBase,
            contentColor = White,
            disabledBackgroundColor = DayGrayscale400,
            disabledContentColor = White,
        ),
        content = content
    )
}

@Composable
fun SquarePrimaryButton(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    type: ButtonSize = ButtonSize.NORMAL,
    content: @Composable RowScope.() -> Unit = {},
) {
    BaseSquareButton(
        onClick = onClick,
        type = type,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = DayBlueBase,
            contentColor = White,
            disabledBackgroundColor = DayGrayscale400,
            disabledContentColor = White,
        ),
        content = content
    )
}

@Preview
@Composable
fun PreviewButtons() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        RoundedPrimaryButton() { Text("Text for Test") }
        SquarePrimaryButton() { Text("Text for Test") }
    }
}