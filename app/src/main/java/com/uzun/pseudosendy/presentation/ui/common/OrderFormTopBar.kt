package com.uzun.pseudosendy.presentation.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uzun.pseudosendy.R
import com.uzun.pseudosendy.presentation._const.UIConst

@Composable
fun OrderFormTopBar(
    onClickBackButton: () -> Unit,
    onClickDeleteButton: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = UIConst.SPACE_XS)
            .padding(vertical = 11.dp)
    ) {
        TopBackIconButton(onClick = onClickBackButton)
        TopDeleteIconButton(onClick = onClickDeleteButton)
    }
}

@Composable
fun BoxScope.TopBackIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier.align(Alignment.CenterStart),
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back_24),
            contentDescription = "back button"
        )
    }
}

@Composable
fun BoxScope.TopDeleteIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier.align(Alignment.CenterEnd),
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_delete_bin),
            contentDescription = "delete written form"
        )
    }
}