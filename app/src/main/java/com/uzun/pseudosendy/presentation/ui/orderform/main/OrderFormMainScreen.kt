package com.uzun.pseudosendy.presentation.ui.orderform.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.uzun.pseudosendy.presentation._const.UIConst.SPACE_XL
import com.uzun.pseudosendy.presentation._const.UIConst.SPACE_XS
import com.uzun.pseudosendy.presentation.model._enum.CardType
import com.uzun.pseudosendy.presentation.model._enum.TypeCardState
import com.uzun.pseudosendy.presentation.ui.common.FloatingRoundBottomButton
import com.uzun.pseudosendy.presentation.ui.orderform.OrderFormContract
import com.uzun.pseudosendy.presentation.ui.orderform.OrderFormContract.OrderFormUiEvent
import com.uzun.pseudosendy.presentation.ui.orderform.OrderFormContract.OrderFormUiSideEffect
import com.uzun.pseudosendy.presentation.ui.orderform.OrderFormContract.OrderFormUiSideEffect.*
import com.uzun.pseudosendy.presentation.ui.orderform.OrderFormViewModel
import com.uzun.pseudosendy.ui.theme.DayGrayscale300
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme
import kotlinx.coroutines.flow.StateFlow

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
                ShowDeleteFormDialog -> {  }
                is ShowToast -> {}
            }
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .padding(horizontal = SPACE_XL)
    ) {
        OrderFormContent(
            uiState = vm.uiState,
            getStateOf = vm::getStateOf,
            onCardClickedList = vm.onCardClickedList
        )
        CheckTransportationFeeButton(vm.uiState) { vm.setEvent(OrderFormUiEvent.OnPaySelectionButtonClicked) }
    }
}

@Composable
fun OrderFormContent(
    getStateOf: (CardType) -> TypeCardState,
    onCardClickedList: List<() -> Unit>,
    uiState: StateFlow<OrderFormContract.OrderFormUiState>,
) =
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        orderFormMainHeadTextArea()
        item { Spacer(Modifier.size(SPACE_XL)) }
        orderTypeCards(getStateOf, onCardClickedList, uiState)
    }

fun LazyListScope.orderFormMainHeadTextArea() = item {
    Text(text = "?????? ?????? ????????????", style = PseudoSendyTheme.typography.XLBold)
    Spacer(Modifier.size(SPACE_XS))
    Text(text = "???????????? ???????????? ??????????????????", style = PseudoSendyTheme.typography.Normal)
}

@Composable
fun StyledText(
    text: String = ""
) = Text(text, style = PseudoSendyTheme.typography.XS.copy(color = DayGrayscale300))

fun LazyListScope.orderTypeCards(
    getStateOf: (CardType) -> TypeCardState,
    onCardClickedList: List<() -> Unit>,
    uiState: StateFlow<OrderFormContract.OrderFormUiState>,
) {
    CardType.values().forEachIndexed { index, cardType ->
        item {
            OrderTypeCard(
                type = cardType,
                state = getStateOf(cardType),
                onClick = onCardClickedList[index]
            ) {
                if(getStateOf(cardType) is TypeCardState.FILLED)
                    OrderTypeCardFilledContent(cardType, uiState)
            }
            Spacer(Modifier.size(SPACE_XS))
        }
    }
    item { Spacer(Modifier.size(100.dp)) }
}

@Composable
fun OrderTypeCardFilledContent(
    cardType: CardType,
    uiState: StateFlow<OrderFormContract.OrderFormUiState>
) = Column {
    val states by uiState.collectAsState()
    when (cardType) {
        CardType.DATETIME -> {
            val date = states.dateTime.date.split("-")
            val dateString = date[0] + "??? " + date[1] + "??? " + date[2] + "??? "
            val time = states.dateTime.time.split(":")
            val timeString = time[0] + "??? " + time[1] + "???"
            StyledText(dateString + timeString)
        }
        CardType.LOCATION -> {
            StyledText("????????? : " + states.locations.depart.roadAddr)
            StyledText("????????? : " + states.locations.arrive.roadAddr)
        }
        CardType.VEHICLE -> {
            StyledText(states.vehicleOptions.vehicleType + " " + states.vehicleOptions.vehicleOption)
        }
        CardType.LOAD_DETAIL -> {
            StyledText(states.loadDetail.value)
        }
        CardType.SERVICE_OPTION -> {
            StyledText("??? ?????? : " + states.serviceOptions.serviceOption)
            StyledText("?????? ?????? : " + if(states.serviceOptions.rideWith) "??????" else "?????????")
        }
    }
}

@Composable
fun BoxScope.CheckTransportationFeeButton(
    uiState: StateFlow<OrderFormContract.OrderFormUiState>,
    onClick: () -> Unit,
) {
    val state by uiState.collectAsState()
    FloatingRoundBottomButton("???????????? ????????????", state.isFormCompleted, onClick)
}

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