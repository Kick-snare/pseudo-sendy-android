package com.uzun.pseudosendy.presentation.ui.orderform.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.uzun.pseudosendy.presentation._const.UIConst.SPACE_XL
import com.uzun.pseudosendy.presentation._const.UIConst.SPACE_XS
import com.uzun.pseudosendy.presentation.model._enum.CardType
import com.uzun.pseudosendy.presentation.model._enum.TypeCardState
import com.uzun.pseudosendy.presentation.ui.common.FloatingRoundBottomButton
import com.uzun.pseudosendy.presentation.ui.orderform.OrderFormContract.OrderFormUiEvent
import com.uzun.pseudosendy.presentation.ui.orderform.OrderFormContract.OrderFormUiSideEffect
import com.uzun.pseudosendy.presentation.ui.orderform.OrderFormContract.OrderFormUiSideEffect.*
import com.uzun.pseudosendy.presentation.ui.orderform.OrderFormViewModel
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme

@Composable
fun OrderFormMainScreen(
    cardNavMap: Map<OrderFormUiSideEffect, () -> Unit>,
    vm: OrderFormViewModel = hiltViewModel(),
) {
    LaunchedEffect(vm.effect) {
        vm.effect.collect { effect ->
            when (effect) {
                NavigateToDateTimeScreen -> cardNavMap[NavigateToDateTimeScreen]?.invoke()
                NavigateToLoadDetailScreen -> cardNavMap[NavigateToLoadDetailScreen]?.invoke()
                NavigateToLocationScreen -> cardNavMap[NavigateToLocationScreen]?.invoke()
                NavigateToServiceOptionScreen -> cardNavMap[NavigateToServiceOptionScreen]?.invoke()
                NavigateToVehicleScreen -> cardNavMap[NavigateToVehicleScreen]?.invoke()
                NavigateToPaySelectionScreen -> cardNavMap[NavigateToPaySelectionScreen]?.invoke()
                ShowDeleteFormDialog -> TODO()
                is ShowToast -> TODO()
            }
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .padding(horizontal = SPACE_XL)
    ) {
        OrderFormContent(
            getStateOf = vm::getStateOf,
            onCardClickedList = vm.onCardClickedList
        )
        CheckTransportationFeeButton { vm.setEvent(OrderFormUiEvent.OnPaySelectionButtonClicked) }
    }
}

@Composable
fun OrderFormContent(
    getStateOf: (CardType) -> TypeCardState,
    onCardClickedList: List<() -> Unit>,
) =
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        orderFormMainHeadTextArea()
        item { Spacer(Modifier.size(SPACE_XL)) }
        orderTypeCards(getStateOf, onCardClickedList)
    }

fun LazyListScope.orderFormMainHeadTextArea() = item {
    Text(text = "센디 용달 예약하기", style = PseudoSendyTheme.typography.XLBold)
    Spacer(Modifier.size(SPACE_XS))
    Text(text = "원하시는 항목부터 입력해주세요", style = PseudoSendyTheme.typography.Normal)
}

fun LazyListScope.orderTypeCards(
    getStateOf: (CardType) -> TypeCardState,
    onCardClickedList: List<() -> Unit>,
) {
    CardType.values().forEachIndexed { index, cardType ->
        item {
            OrderTypeCard(
                type = cardType,
                state = getStateOf(cardType),
                onClick = onCardClickedList[index]
            ) {
                when (cardType) {
                    CardType.DATETIME -> {}
                    CardType.LOCATION -> {}
                    CardType.VEHICLE -> {}
                    CardType.LOAD_DETAIL -> {}
                    CardType.SERVICE_OPTION -> {}
                }
            }
            Spacer(Modifier.size(SPACE_XS))
        }
    }
}

@Composable
fun BoxScope.CheckTransportationFeeButton(onClick: () -> Unit) =
    FloatingRoundBottomButton("운송비용 확인하기", onClick)

@Preview
@Composable
fun PreviewOrderFormMainScreen() {
    PseudoSendyTheme {
        Column(
            Modifier
                .background(Color.White)
                .fillMaxSize()
        ) {
//            OrderFormMainScreen()
        }
    }
}