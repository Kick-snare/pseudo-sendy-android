package com.uzun.pseudosendy.presentation.ui.orderform.serviceoption

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uzun.pseudosendy.R
import com.uzun.pseudosendy.presentation._const.UIConst
import com.uzun.pseudosendy.presentation.ui.common.FormDetailBaseScreen
import com.uzun.pseudosendy.presentation.ui.common.RoundInputField
import com.uzun.pseudosendy.presentation.ui.orderform.main.CardType
import com.uzun.pseudosendy.presentation.ui.orderform.vehicle.DropDownIconButton
import com.uzun.pseudosendy.ui.theme.DayBlueBase
import com.uzun.pseudosendy.ui.theme.DayGrayscale100
import com.uzun.pseudosendy.ui.theme.DayGrayscale300
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme

@Composable
fun ServiceOptionScreen() {

    FormDetailBaseScreen(
        cardType = CardType.SERVICE_OPTION,
        arrangement = Arrangement.spacedBy(UIConst.SPACE_XS),
        onButtonClicked = {},
    ) {
        labelText("운반에 기사님 도움이 필요한가요?")
        dropdownSelector()

        item { Spacer(Modifier.size(UIConst.SPACE_XL)) }

        labelText("차량 동승이 필요하신가요?")
        checkBoxField()
    }
}

fun LazyListScope.labelText(text: String) = item {
    Text(
        text = text,
        style = PseudoSendyTheme.typography.Small,
        color = DayGrayscale100
    )
}

fun LazyListScope.dropdownSelector(onClick: () -> Unit = {}) = item {
    RoundInputField(
        onClick = onClick,
        extraContent = { DropDownIconButton() },
        content = { Text("본인이 직접 옮김") }
    )
}

fun LazyListScope.checkBoxField(
    onClick: () -> Unit = {},
) = item {
    var isChecked by remember { mutableStateOf(false) }
    RoundInputField(
        modifier =
        if (isChecked)
            Modifier.border(
                1.dp,
                DayBlueBase,
                RoundedCornerShape(UIConst.BUTTON_RADIUS_NORMAL)
            )
        else Modifier,
        onClick = {
            onClick()
            isChecked = !isChecked
        },
        content = {
            CheckBoxIcon(isChecked)
            CheckBoxFieldContent(isChecked)
        }
    )
}

@Composable
fun CheckBoxIcon(isChecked: Boolean) = Image(
    painterResource(
        id = if (isChecked) R.drawable.checkbox_checked
        else R.drawable.checkbox_un_checked
    ),
    modifier = Modifier
        .padding(end = UIConst.SPACE_S)
        .size(16.dp),
    contentDescription = null
)

@Composable
fun CheckBoxFieldContent(isChecked: Boolean) = Text(
    text = "운반 차량에 함께 탑승",
    color = if(isChecked) DayGrayscale100 else DayGrayscale300,
    style = PseudoSendyTheme.typography.Small
)