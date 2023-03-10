package com.uzun.pseudosendy.presentation.ui.orderform

import androidx.lifecycle.viewModelScope
import com.uzun.pseudosendy.data.repository.MapsRepository
import com.uzun.pseudosendy.presentation.model.LngLat
import com.uzun.pseudosendy.presentation.model.Location
import com.uzun.pseudosendy.presentation.model._enum.CardType
import com.uzun.pseudosendy.presentation.model._enum.TypeCardState
import com.uzun.pseudosendy.presentation.model._enum.TypeCardState.ERROR
import com.uzun.pseudosendy.presentation.model._enum.TypeCardState.FILLED
import com.uzun.pseudosendy.presentation.ui.common.base.BaseViewModel
import com.uzun.pseudosendy.presentation.ui.orderform.OrderFormContract.*
import com.uzun.pseudosendy.presentation.ui.orderform.OrderFormContract.OrderFormUiEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderFormViewModel @Inject constructor(
    private val mapsRepository: MapsRepository,
) : BaseViewModel<OrderFormUiState, OrderFormUiSideEffect, OrderFormUiEvent>(OrderFormUiState()) {

    val onCardClickedList: List<() -> Unit> by lazy {
        var list = emptyList<() -> Unit>().toMutableList()
        CardType.values().forEach { list.add { setEvent(getUiEventFor(it)) } }
        list
    }

    private fun getUiEventFor(type: CardType) = when (type) {
        CardType.DATETIME -> OnDateTimeCardClicked
        CardType.LOCATION -> OnLocationCardClicked
        CardType.VEHICLE -> OnVehicleCardClicked
        CardType.LOAD_DETAIL -> OnLoadDetailCardClicked
        CardType.SERVICE_OPTION -> OnServiceOptionCardClicked
    }

    fun getStateOf(type: CardType): TypeCardState = when (type) {
        CardType.DATETIME -> this.uiState.value.dateTimeTypeState
        CardType.LOCATION -> this.uiState.value.locationTypeState
        CardType.VEHICLE -> this.uiState.value.vehicleTypeState
        CardType.LOAD_DETAIL -> this.uiState.value.loadDetailTypeState
        CardType.SERVICE_OPTION -> this.uiState.value.serviceOptionTypeState
    }

    override suspend fun handleEvent(event: OrderFormUiEvent) {
        when (event) {
            OnDeleteButtonClicked ->
                setEffect(OrderFormUiSideEffect.ShowDeleteFormDialog)
            OnDateTimeCardClicked ->
                setEffect(OrderFormUiSideEffect.NavigateToDateTimeScreen)
            OnLocationCardClicked ->
                setEffect(OrderFormUiSideEffect.NavigateToLocationScreen)
            OnVehicleCardClicked ->
                setEffect(OrderFormUiSideEffect.NavigateToVehicleScreen)
            OnLoadDetailCardClicked ->
                setEffect(OrderFormUiSideEffect.NavigateToLoadDetailScreen)
            OnServiceOptionCardClicked ->
                setEffect(OrderFormUiSideEffect.NavigateToServiceOptionScreen)
            OnPaySelectionButtonClicked -> {
                setState { copy(isFormCompleted = true) }
                setEffect(OrderFormUiSideEffect.NavigateToPaySelectionScreen)
            }
            is OnDateSelected -> onDateSelected(event.value)
            is OnTimeSelected -> onTimeSelected(event.value)
            is OnDepartSelected -> onDepartSelected(event.value)
            is OnArriveSelected -> onArriveSelected(event.value)
            is OnLoadDetailEntered -> onLoadDetailEntered(event.value)
            is OnVehicleTypeSelected -> onVehicleTypeSelected(event.value)
            is OnVehicleOptionSelected -> onVehicleOptionSelected(event.value)
            is OnServiceOptionSelected -> onServiceOptionSelected(event.value)
            is OnRideWithOptionClicked -> onRideWithOptionClicked(event.value)
            is OnLocationSearched -> getSearchedLocationList(event.value)
        }
    }

    private fun onDateSelected(value: String) {
        val newDateTime = this.uiState.value.dateTime.copy(date = value)
        setState { this.copy(dateTime = newDateTime) }
    }

    private fun onTimeSelected(value: String) {
        val newDateTime = this.uiState.value.dateTime.copy(time = value)
        setState { this.copy(dateTime = newDateTime) }
    }

    fun onInputCompleted(
        cardType: CardType,
        popBack: () -> Unit,
    ) {
        when (cardType) {
            CardType.DATETIME -> {
                if (uiState.value.dateTime.isCompleted())
                    setState { this.copy(dateTimeTypeState = FILLED) }
                else setState { this.copy(dateTimeTypeState = ERROR()) }
            }
            CardType.LOCATION -> {
                if (uiState.value.locations.isCompleted())
                    setState { this.copy(locationTypeState = FILLED) }
                else setState { this.copy(locationTypeState = ERROR()) }
            }
            CardType.VEHICLE -> {
                if (uiState.value.vehicleOptions.isCompleted())
                    setState { this.copy(vehicleTypeState = FILLED) }
                else setState { this.copy(vehicleTypeState = ERROR()) }
            }
            CardType.LOAD_DETAIL -> {
                if (uiState.value.loadDetail.isCompleted())
                    setState { this.copy(loadDetailTypeState = FILLED) }
                else setState { this.copy(loadDetailTypeState = ERROR()) }
            }
            CardType.SERVICE_OPTION -> {
                if (uiState.value.serviceOptions.isCompleted())
                    setState { this.copy(serviceOptionTypeState = FILLED) }
                else setState { this.copy(serviceOptionTypeState = ERROR()) }
            }
        }
        checkAllFormFilled()
        popBack()
    }

    private fun onDepartSelected(value: Location) {
        val newLoc = this.uiState.value.locations.copy(depart = value)
        setState { this.copy(locations = newLoc) }
    }

    private fun onArriveSelected(value: Location) {
        val newLoc = this.uiState.value.locations.copy(arrive = value)
        setState { this.copy(locations = newLoc) }
    }

    private fun onLoadDetailEntered(value: String) {
        val newDetail = this.uiState.value.loadDetail.copy(value = value)
        setState { this.copy(loadDetail = newDetail) }
    }

    private fun onVehicleTypeSelected(value: String) {
        val newVehicleOptions = this.uiState.value.vehicleOptions.copy(vehicleType = value)
        setState { this.copy(vehicleOptions = newVehicleOptions) }
    }

    private fun onVehicleOptionSelected(value: String) {
        val newVehicleOptions = this.uiState.value.vehicleOptions.copy(vehicleOption = value)
        setState { this.copy(vehicleOptions = newVehicleOptions) }
    }

    private fun onServiceOptionSelected(value: String) {
        val newServiceOption = this.uiState.value.serviceOptions.copy(serviceOption = value)
        setState { this.copy(serviceOptions = newServiceOption) }
    }

    private fun onRideWithOptionClicked(value: Boolean) {
        val newServiceOption = this.uiState.value.serviceOptions.copy(rideWith = value)
        setState { this.copy(serviceOptions = newServiceOption) }
    }

    private fun getSearchedLocationList(query: String) {
        viewModelScope.launch {
            val res = mapsRepository.getLocationBy(query)
            if (res.status != "200") return@launch
            setState {
                this.copy(locationList = res.addresses.map {
                    Location(
                        it.jibunAddress,
                        it.roadAddress,
                        LngLat(it.x.toDouble(), it.y.toDouble())
                    )
                })
            }
        }
    }

    private fun checkAllFormFilled() {
        var isAllFilled = true
        CardType.values().map { getStateOf(it) }.forEach { if(!(it is FILLED)) isAllFilled = false }
        setState { this.copy(isFormCompleted = isAllFilled) }
//        setState { this.copy(isFormCompleted = true) }
    }
}