package com.uzun.pseudosendy.presentation.ui.orderform.datetime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.uzun.pseudosendy.R
import com.uzun.pseudosendy.presentation._const.UIConst
import com.uzun.pseudosendy.presentation.ui.common.FormDetailBaseScreen
import com.uzun.pseudosendy.presentation.ui.common.RoundInputField
import com.uzun.pseudosendy.presentation.ui.orderform.main.CardType
import com.uzun.pseudosendy.ui.theme.DayGrayscale100
import com.uzun.pseudosendy.ui.theme.DayGrayscale400
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme

@Preview
@Composable
fun DateTimeScreen(
    // hiltViewModel()
) = FormDetailBaseScreen(
    cardType = CardType.DATETIME,
    arrangement = Arrangement.spacedBy(UIConst.SPACE_XS),
    onButtonClicked = {},
) {
    guideText()
    datePicker(
        value = "",
        onValueChange = {},
    )
    timePicker {}
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
    var text by remember{ mutableStateOf("") } // TODO 추후 상태를 Intent로 관리하도록 변경 (MVI)
    DatePicker(value = text, onValueChange = { text=it }) { showDialog ->
        RoundInputField(
            onClick = showDialog,
            extraContent = { EndedRightArrowIcon() },
            content = { DateWithIcon(R.drawable.ic_clock, text) }
        )
    }
}

fun LazyListScope.timePicker(onClick: () -> Unit) = item {
    RoundInputField(
        onClick = onClick,
        extraContent = { EndedRightArrowIcon() },
        content = {

        }
    )
}

@Composable
fun BoxScope.EndedRightArrowIcon() = Icon(
    painterResource(id = R.drawable.navigation_chevron_right_24),
    contentDescription = null,
    modifier = Modifier.align(Alignment.CenterEnd)
)

@Composable
fun DateWithIcon(iconId: Int, value: String) {
    Icon(
        painterResource(id = iconId),
        contentDescription = null,
    )
    Spacer(modifier = Modifier.size(UIConst.SPACE_XXS))

    val formattedDate by lazy {
        val ymd = value.split("-")
        runCatching { "" + ymd[0] + "년 " + ymd[1] + "월 " + ymd[2] + "일" }.getOrElse { "" }
    }

    Text(
        text = formattedDate.ifBlank { "날짜 선택하기" },
        style = PseudoSendyTheme.typography.Small.copy(
            color = if(value.isNotBlank()) DayGrayscale100 else DayGrayscale400
        )
    )
}