package com.uzun.pseudosendy.presentation.ui.orderform

import android.util.Log
import com.uzun.pseudosendy.presentation.ui.common.base.BaseViewModel
import com.uzun.pseudosendy.presentation.ui.orderform.OrderFormContract.*
import com.uzun.pseudosendy.presentation.ui.orderform.main.CardType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class OrderFormViewModel @Inject constructor(
    // some repositories inject 해야됨...
) : BaseViewModel<OrderFormUiState, OrderFormUiSideEffect, OrderFormUiEvent>(OrderFormUiState()) {

    val onCardClickedList : List<() -> Unit> by lazy {
        var list = emptyList<() -> Unit>().toMutableList()
        CardType.values().forEach { list.add { setEvent(getUiEventFor(it)) } }
        list
    }

    override suspend fun handleEvent(event: OrderFormUiEvent) {
        Log.e("test", event.toString())
        when(event) {
            OrderFormUiEvent.OnDeleteButtonClicked -> {
                setEffect(OrderFormUiSideEffect.ShowDeleteFormDialog)
            }
            OrderFormUiEvent.OnDateTimeCardClicked -> {
                setEffect(OrderFormUiSideEffect.NavigateToDateTimeScreen)
            }
            OrderFormUiEvent.OnLocationCardClicked -> {
                setEffect(OrderFormUiSideEffect.NavigateToLocationScreen)
            }
            OrderFormUiEvent.OnVehicleCardClicked -> {
                setEffect(OrderFormUiSideEffect.NavigateToVehicleScreen)
            }
            OrderFormUiEvent.OnLoadDetailCardClicked -> {
                setEffect(OrderFormUiSideEffect.NavigateToLoadDetailScreen)
            }
            OrderFormUiEvent.OnServiceOptionCardClicked -> {
                setEffect(OrderFormUiSideEffect.NavigateToServiceOptionScreen)
            }
            OrderFormUiEvent.OnPaySelectionButtonClicked -> {
                setState { copy(isFormCompleted = true) }
                setEffect(OrderFormUiSideEffect.NavigateToPaySelectionScreen)
            }
        }
    }

    fun getUiEventFor(type: CardType) = when (type) {
        CardType.DATETIME -> OrderFormUiEvent.OnDateTimeCardClicked
        CardType.LOCATION -> OrderFormUiEvent.OnLocationCardClicked
        CardType.VEHICLE -> OrderFormUiEvent.OnVehicleCardClicked
        CardType.LOAD_DETAIL -> OrderFormUiEvent.OnLoadDetailCardClicked
        CardType.SERVICE_OPTION -> OrderFormUiEvent.OnServiceOptionCardClicked
    }
}