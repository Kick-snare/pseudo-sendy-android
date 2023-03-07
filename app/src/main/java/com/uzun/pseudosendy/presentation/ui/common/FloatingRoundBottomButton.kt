package com.uzun.pseudosendy.presentation.ui.common

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.uzun.pseudosendy.presentation._const.UIConst
import com.uzun.pseudosendy.presentation.ui.common.RoundedPrimaryButton
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme

@Composable
fun BoxScope.FloatingRoundBottomButton(
    text: String,
    onClick: () -> Unit,
) {
    RoundedPrimaryButton(
        onClick = onClick,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = UIConst.SPACE_XL),
    ) {
        Text(
            text = text,
            style = PseudoSendyTheme.typography.Normal
        )
    }
}