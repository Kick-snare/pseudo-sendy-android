package com.uzun.pseudosendy.presentation.ui.orderform.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.uzun.pseudosendy.presentation._const.UIConst.SPACE_XL
import com.uzun.pseudosendy.presentation._const.UIConst.SPACE_XS
import com.uzun.pseudosendy.presentation.ui.common.RoundedPrimaryButton
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme

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
fun BoxScope.OrderFormContent() =
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        orderFormMainHeadTextArea()
        item { Spacer(Modifier.size(SPACE_XL)) }
        orderTypeCards()
    }

fun LazyListScope.orderFormMainHeadTextArea() = item {
    Text(text = "센디 용달 예약하기", style = PseudoSendyTheme.typography.XLBold)
    Spacer(Modifier.size(SPACE_XS))
    Text(text = "원하시는 항목부터 입력해주세요", style = PseudoSendyTheme.typography.Normal)
}

fun LazyListScope.orderTypeCards() {
    CardType.values().forEach { cardType ->
        item{
            OrderTypeCard(type = cardType) {}
            Spacer(Modifier.size(SPACE_XS))
        }
    }
}

@Composable
fun BoxScope.CheckTransportationFeeButton(
    onClick: () -> Unit,
) {
    RoundedPrimaryButton(
        onClick = onClick,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = SPACE_XL),
    ) {
        Text(
            text = "운송비용 확인하기",
            style = PseudoSendyTheme.typography.Normal
        )
    }
}

@Preview
@Composable
fun PreviewOrderFormMainScreen() {
    PseudoSendyTheme {
        Column(Modifier.background(Color.White).fillMaxSize()) {
            OrderFormMainScreen()
        }
    }
}