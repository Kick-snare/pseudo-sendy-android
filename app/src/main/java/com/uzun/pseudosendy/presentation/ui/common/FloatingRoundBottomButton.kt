package com.uzun.pseudosendy.presentation.ui.common

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.uzun.pseudosendy.presentation._const.UIConst
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme

@Composable
fun BoxScope.FloatingRoundBottomButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    RoundedPrimaryButton(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = UIConst.SPACE_XL),
    ) {
        Text(
            text = text,
            style = PseudoSendyTheme.typography.Normal,
            color = Color.White
        )
    }
}