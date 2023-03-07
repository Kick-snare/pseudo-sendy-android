package com.uzun.pseudosendy.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.uzun.pseudosendy.presentation._const.UIConst
import com.uzun.pseudosendy.ui.theme.DayBackgroundSecondary

@Composable
fun RoundGreyInputField(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    extraContent: @Composable BoxScope.() -> Unit = {},
    content: @Composable RowScope.() -> Unit = {},
) = Box(
    Modifier
        .clickable(onClick = onClick)
        .background(DayBackgroundSecondary, RoundedCornerShape(UIConst.BUTTON_RADIUS_NORMAL))
        .fillMaxWidth()
        .padding(horizontal = UIConst.SPACE_M)
        .padding(vertical = UIConst.SPACE_S),
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
    extraContent()
}