package com.uzun.pseudosendy.presentation.ui.orderform

import com.uzun.pseudosendy.presentation.model.*
import com.uzun.pseudosendy.presentation.model._enum.TypeCardState
import com.uzun.pseudosendy.presentation.model._enum.TypeCardState.DEFAULT
import com.uzun.pseudosendy.presentation.ui.common.base.UiEvent
import com.uzun.pseudosendy.presentation.ui.common.base.UiSideEffect
import com.uzun.pseudosendy.presentation.ui.common.base.UiState

class OrderFormContract {

    data class OrderFormUiState(
        val isLoading: Boolean = false,
        val isFormCompleted: Boolean = false,
        val dateTimeTypeState: TypeCardState = DEFAULT,
        val locationTypeState: TypeCardState = DEFAULT,
        val vehicleTypeState: TypeCardState = DEFAULT,
        val loadDetailTypeState: TypeCardState = DEFAULT,
        val serviceOptionTypeState: TypeCardState = DEFAULT,

        val dateTime: DateTime = DateTime(),
        val locations: Locations = Locations(),
        val locationList: List<Location> = emptyList(),
        val vehicleOptions: VehicleOptions = VehicleOptions(),
        val loadDetail: LoadDetail = LoadDetail(),
        val serviceOptions: ServiceOptions = ServiceOptions(),
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
        data class OnDateSelected(val value: String) : OrderFormUiEvent()
        data class OnTimeSelected(val value: String) : OrderFormUiEvent()

        object OnLocationCardClicked : OrderFormUiEvent()
        data class OnDepartSelected(val value: Location) : OrderFormUiEvent()
        data class OnArriveSelected(val value: Location) : OrderFormUiEvent()

        data class OnLocationSearched(val value: String) : OrderFormUiEvent()

        object OnVehicleCardClicked : OrderFormUiEvent()
        data class OnVehicleTypeSelected(val value: String) : OrderFormUiEvent()
        data class OnVehicleOptionSelected(val value: String) : OrderFormUiEvent()

        object OnLoadDetailCardClicked : OrderFormUiEvent()
        data class OnLoadDetailEntered(val value: String) : OrderFormUiEvent()

        object OnServiceOptionCardClicked : OrderFormUiEvent()
        data class OnServiceOptionSelected(val value: String) : OrderFormUiEvent()
        data class OnRideWithOptionClicked(val value: Boolean) : OrderFormUiEvent()

        object OnPaySelectionButtonClicked : OrderFormUiEvent()
        object OnDeleteButtonClicked : OrderFormUiEvent()
    }
}