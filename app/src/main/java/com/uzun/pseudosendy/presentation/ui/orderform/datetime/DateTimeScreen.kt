package com.uzun.pseudosendy.presentation.ui.orderform.datetime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.uzun.pseudosendy.R
import com.uzun.pseudosendy.presentation._const.UIConst
import com.uzun.pseudosendy.presentation.model.DateTime
import com.uzun.pseudosendy.presentation.model._enum.CardType
import com.uzun.pseudosendy.presentation.ui.common.FormDetailBaseScreen
import com.uzun.pseudosendy.presentation.ui.common.RoundInputField
import com.uzun.pseudosendy.ui.theme.DayGrayscale100
import com.uzun.pseudosendy.ui.theme.DayGrayscale400
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme

@Composable
fun DateTimeScreen(
    dateTime: DateTime = DateTime(),
    onDateChanged: (String) -> Unit = {},
    onTimeChanged: (String) -> Unit = {},
    onInputCompleted: () -> Unit = {},
) = FormDetailBaseScreen(
    cardType = CardType.DATETIME,
    arrangement = Arrangement.spacedBy(UIConst.SPACE_XS),
    onButtonClicked = onInputCompleted,
) {
    guideText()
    datePicker(
        value = dateTime.date,
        onValueChange = onDateChanged,
    )
    timePicker(
        value = dateTime.time,
        onValueChange = onTimeChanged
    )
}

fun LazyListScope.guideText() = item {
    Text(
        text = "운송 시작일을 알려주세요.",
        style = PseudoSendyTheme.typography.Small
    )
}

fun LazyListScope.datePicker(
    value: String,
    onValueChange: (String) -> Unit,
) = item {
    DatePicker(
        value = value, onValueChange = onValueChange
    ) { formattedDate, showDialog ->
        RoundInputField(
            onClick = showDialog,
            extraContent = { EndedRightArrowIcon() },
            content = {
                TextWithIcon(
                    iconId = R.drawable.ic_clock,
                    value = formattedDate,
                    hint = "날짜 선택하기"
                )
            }
        )
    }
}

fun LazyListScope.timePicker(
    value: String,
    onValueChange: (String) -> Unit,
) = item {
    TimePicker(
        value = value,
        onValueChange = onValueChange
    ) {time, showDialog ->
        RoundInputField(
            onClick = showDialog,
            extraContent = { EndedRightArrowIcon() },
            content = {
                TextWithIcon(
                    iconId = R.drawable.ic_clock,
                    value = time,
                    hint = "시간 선택하기"
                )
            }
        )
    }
}

@Composable
fun BoxScope.EndedRightArrowIcon() = Icon(
    painterResource(id = R.drawable.navigation_chevron_right_24),
    contentDescription = null,
    modifier = Modifier.align(Alignment.CenterEnd)
)

@Composable
fun TextWithIcon(
    iconId: Int,
    value: String,
    hint: String,
) {
    Icon(
        painterResource(id = iconId),
        contentDescription = null,
    )
    Spacer(modifier = Modifier.size(UIConst.SPACE_XXS))

    Text(
        text = value.ifBlank { hint },
        style = PseudoSendyTheme.typography.Small.copy(
            color = if(value.isNotBlank()) DayGrayscale100 else DayGrayscale400
        )
    )
}