package com.uzun.pseudosendy.presentation.ui.orderform

import com.uzun.pseudosendy.presentation.ui.common.base.UiEvent
import com.uzun.pseudosendy.presentation.ui.common.base.UiSideEffect
import com.uzun.pseudosendy.presentation.ui.common.base.UiState
import com.uzun.pseudosendy.presentation.ui.orderform.main.CardType
import com.uzun.pseudosendy.presentation.ui.orderform.main.TypeCardState

class OrderFormContract {

    data class OrderFormUiState(
        val isLoading: Boolean = false,
        val isFormCompleted: Boolean = false,
        val formTypeStateList : MutableMap<CardType, TypeCardState>
            = emptyMap<CardType, TypeCardState>().toMutableMap()
    ) : UiState

    sealed class OrderFormUiSideEffect : UiSideEffect {
        object NavigateToDateTimeScreen : OrderFormUiSideEffect()
        object NavigateToLocationScreen : OrderFormUiSideEffect()
        object NavigateToVehicleScreen : OrderFormUiSideEffect()
        object NavigateToLoadDetailScreen : OrderFormUiSideEffect()
        object NavigateToServiceOptionScreen : OrderFormUiSideEffect()
        object NavigateToPaySelectionScreen : OrderFormUiSideEffect()
        object ShowDeleteFormDialog : OrderFormUiSideEffect()
        data class ShowToast(val msg: String) : OrderFormUiSideEffect()
    }

    sealed class OrderFormUiEvent : UiEvent {
        object OnDateTimeCardClicked : OrderFormUiEvent()
        object OnLocationCardClicked : OrderFormUiEvent()
        object OnVehicleCardClicked : OrderFormUiEvent()
        object OnLoadDetailCardClicked : OrderFormUiEvent()
        object OnServiceOptionCardClicked : OrderFormUiEvent()
        object OnPaySelectionButtonClicked : OrderFormUiEvent()
        object OnDeleteButtonClicked : OrderFormUiEvent()
    }
}