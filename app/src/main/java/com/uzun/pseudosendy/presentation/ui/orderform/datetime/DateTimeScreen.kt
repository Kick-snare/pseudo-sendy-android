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
import androidx.compose.ui.tooling.preview.Preview
import com.uzun.pseudosendy.R
import com.uzun.pseudosendy.presentation._const.UIConst
import com.uzun.pseudosendy.presentation.ui.common.FormDetailBaseScreen
import com.uzun.pseudosendy.presentation.ui.common.RoundGreyInputField
import com.uzun.pseudosendy.presentation.ui.orderform.main.CardType
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
    datePicker {}
    timePicker {}
}

fun LazyListScope.guideText() = item {
    Text(
        text = "필요한 차량과 차량 옵션을 모두 선택해주세요.",
        style = PseudoSendyTheme.typography.Small
    )
}

fun LazyListScope.datePicker(onClick: () -> Unit) = item {
    RoundGreyInputField(
        onClick = onClick,
        extraContent = { EndedRightArrowIcon() },
        content = { IconWithGreyText(R.drawable.ic_date, "날짜 선택하기") }
    )
}

fun LazyListScope.timePicker(onClick: () -> Unit) = item {
    RoundGreyInputField(
        onClick = onClick,
        extraContent = { EndedRightArrowIcon() },
        content = { IconWithGreyText(R.drawable.ic_clock, "시간 선택하기") }
    )
}

@Composable
fun BoxScope.EndedRightArrowIcon()
    = Icon(
        painterResource(id = R.drawable.navigation_chevron_right_24),
        contentDescription = null,
        modifier = Modifier.align(Alignment.CenterEnd)
    )

@Composable
fun IconWithGreyText(
    iconId: Int,
    text: String,
) {
    Icon(
        painterResource(id = iconId),
        contentDescription = null,
    )
    Spacer(modifier = Modifier.size(UIConst.SPACE_XXS))
    Text(
        text = text,
        style = PseudoSendyTheme.typography.Small,
        color = DayGrayscale400
    )
}

