package com.uzun.pseudosendy.presentation.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.uzun.pseudosendy.presentation.ui.orderform.vehicle.DropDownIconButton
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme

@Composable
fun DropDownMenuSelector(
    optionList: List<String>,
    onItemClick: (String) -> Unit = {},
) {
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(optionList.first()) }

    RoundInputField(
        onClick = { isDropDownMenuExpanded = !isDropDownMenuExpanded },
        extraContent = { DropDownIconButton() },
        content = {
            Column {
                Text(text = selectedOption, style = PseudoSendyTheme.typography.Small)

                DropdownMenu(
                    modifier = Modifier.fillMaxWidth(0.7f),
                    expanded = isDropDownMenuExpanded,
                    onDismissRequest = { isDropDownMenuExpanded = false }
                ) {
                    optionList.forEach { option ->
                        DropdownMenuItem(onClick = {
                            selectedOption = option
                            onItemClick(option)
                        }) {
                            Text(
                                text = option,
                                style = PseudoSendyTheme.typography.Small
                            )
                        }
                    }
                }
            }
        }
    )

}