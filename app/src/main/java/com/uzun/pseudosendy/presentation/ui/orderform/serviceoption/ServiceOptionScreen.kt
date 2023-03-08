package com.uzun.pseudosendy.presentation.ui.orderform.serviceoption

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uzun.pseudosendy.R
import com.uzun.pseudosendy.presentation._const.UIConst
import com.uzun.pseudosendy.presentation.ui.common.DropDownMenuSelector
import com.uzun.pseudosendy.presentation.ui.common.FormDetailBaseScreen
import com.uzun.pseudosendy.presentation.ui.common.ModalBottomSheet
import com.uzun.pseudosendy.presentation.ui.common.RoundInputField
import com.uzun.pseudosendy.presentation.ui.orderform.main.CardType
import com.uzun.pseudosendy.ui.theme.DayBlueBase
import com.uzun.pseudosendy.ui.theme.DayGrayscale100
import com.uzun.pseudosendy.ui.theme.DayGrayscale300
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme

@Composable
fun ServiceOptionScreen() = ModalBottomSheet(
    activityContentScope = { onExpanded ->
        ServiceOptionScreenContent(
            expandBottomSheet = onExpanded
        )
    },
    sheetContent = { onHidden ->
        AgreementSheetContent(
            hideBottomSheet = onHidden,
            onAgreement = {}
        )
    }
)

@Composable
fun ServiceOptionScreenContent(
    expandBottomSheet: () -> Unit,
) = FormDetailBaseScreen(
    cardType = CardType.SERVICE_OPTION,
    arrangement = Arrangement.spacedBy(UIConst.SPACE_XS),
    onButtonClicked = {},
) {
    labelText("운반에 기사님 도움이 필요한가요?")
    dropdownSelector()

    item { Spacer(Modifier.size(UIConst.SPACE_XL)) }

    labelText("차량 동승이 필요하신가요?")
    checkBoxField(
        isChecked = false,
        onClick = expandBottomSheet
    )
}

fun LazyListScope.labelText(text: String) = item {
    Text(
        text = text,
        style = PseudoSendyTheme.typography.Small,
        color = DayGrayscale100
    )
}

fun LazyListScope.dropdownSelector(onClick: () -> Unit = {}) = item {
    DropDownMenuSelector(
        optionList =  listOf("본인이 직접 옮김", "상하차만 도움", "상하차 및 운반 도움", "기사님 도움 + 인부 1명 추가"),
        onItemClick = { selectedOption ->
            Log.e("test", "selected option -> $selectedOption")
        }
    )
}

fun LazyListScope.checkBoxField(
    isChecked: Boolean = false,
    onClick: () -> Unit = {},
) = item {
    RoundInputField(
        modifier =
        if (isChecked)
            Modifier.border(
                1.dp,
                DayBlueBase,
                RoundedCornerShape(UIConst.BUTTON_RADIUS_NORMAL)
            )
        else Modifier,
        onClick = onClick,
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
    color = if (isChecked) DayGrayscale100 else DayGrayscale300,
    style = PseudoSendyTheme.typography.Small
)