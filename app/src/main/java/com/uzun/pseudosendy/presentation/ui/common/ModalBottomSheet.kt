package com.uzun.pseudosendy.presentation.ui.common

import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ModalBottomSheet(
    sheetContent : @Composable (onHidden: () -> Unit) -> Unit,
    activityContentScope: @Composable (onExpanded: () -> Unit) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    ModalBottomSheetLayout(
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetState = state,
        sheetContent = { sheetContent{
            scope.launch {
                state.animateTo(ModalBottomSheetValue.Hidden, tween(500))
            }
        }},
        content = { activityContentScope{
            scope.launch {
                state.animateTo(ModalBottomSheetValue.Expanded, tween(500))
            }
        }}
    )
}
