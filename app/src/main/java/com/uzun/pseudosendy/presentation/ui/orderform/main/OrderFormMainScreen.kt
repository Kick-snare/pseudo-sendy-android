package com.uzun.pseudosendy.presentation.ui.orderform.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.uzun.pseudosendy.presentation._const.UIConst.SPACE_XL
import com.uzun.pseudosendy.presentation._const.UIConst.SPACE_XS
import com.uzun.pseudosendy.presentation.ui.common.ButtonSize
import com.uzun.pseudosendy.presentation.ui.common.RoundedPrimaryButton
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme

@Preview
@Composable
fun OrderFormMainScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .padding(horizontal = SPACE_XL)
    ) {
        OrderFormContent()
        CheckTransportationFeeButton {}
    }
}

@Composable
fun BoxScope.OrderFormContent(
    modifier: Modifier = Modifier.fillMaxSize(),
) {
    Column(modifier) {
        OrderFormMainHeadTextArea()
        Spacer(Modifier.size(SPACE_XL))
        OrderTypeCards()
    }
}

@Composable
fun ColumnScope.OrderFormMainHeadTextArea() {
    Text(text = "센디 용달 예약하기", style = PseudoSendyTheme.typography.XLBold)
    Spacer(Modifier.size(SPACE_XS))
    Text(text = "원하시는 항목부터 입력해주세요", style = PseudoSendyTheme.typography.Normal)
}


@Composable
fun OrderTypeCards() {
    Column(verticalArrangement = Arrangement.spacedBy(SPACE_XS)) {
        OrderTypeCard(
            type = CardType.DATETIME,
            onClick = {},
        ) {

        }

        OrderTypeCard(
            type = CardType.LOCATION,
            onClick = {},
        ) { }

        OrderTypeCard(
            type = CardType.VEHICLE,
            onClick = {},
        ) { }

        OrderTypeCard(
            type = CardType.LOAD_DETAIL,
            onClick = {},
        ) { }

        OrderTypeCard(
            type = CardType.SERVICE_OPTION,
            onClick = {},
        ) { }
    }
}

@Composable
fun BoxScope.CheckTransportationFeeButton(
    onClick: () -> Unit,
) {
    RoundedPrimaryButton(
        onClick = onClick,
        modifier = Modifier.align(Alignment.BottomCenter),
        type = ButtonSize.LARGE
    ) {
        Text(
            text = "운송비용 확인하기",
            style = PseudoSendyTheme.typography.Normal
        )
    }
}