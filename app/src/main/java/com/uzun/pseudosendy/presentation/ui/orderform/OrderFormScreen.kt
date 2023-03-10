package com.uzun.pseudosendy.presentation.ui.orderform

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uzun.pseudosendy.presentation._const.UIConst.SPACE_XS
import com.uzun.pseudosendy.presentation.model._enum.CardType
import com.uzun.pseudosendy.presentation.ui.common.OrderFormTopBar
import com.uzun.pseudosendy.presentation.ui.orderform.OrderFormContract.OrderFormUiEvent
import com.uzun.pseudosendy.presentation.ui.orderform.OrderFormContract.OrderFormUiSideEffect
import com.uzun.pseudosendy.presentation.ui.orderform.datetime.DateTimeScreen
import com.uzun.pseudosendy.presentation.ui.orderform.loaddetail.LoadDetailScreen
import com.uzun.pseudosendy.presentation.ui.orderform.location.LocationScreen
import com.uzun.pseudosendy.presentation.ui.orderform.main.OrderFormMainScreen
import com.uzun.pseudosendy.presentation.ui.orderform.serviceoption.ServiceOptionScreen
import com.uzun.pseudosendy.presentation.ui.orderform.vehicle.VehicleScreen
import com.uzun.pseudosendy.presentation.ui.paycheck.PayCheckScreen
import com.uzun.pseudosendy.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderFormScreen(
    navController: NavHostController = rememberNavController(),
    vm: OrderFormViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(Modifier.size(SPACE_XS))

        Scaffold(
            topBar = {
                OrderFormTopBar(
                    onClickBackButton = { navController.navigateUp() },
                    onClickDeleteButton = { vm.setEvent(OrderFormUiEvent.OnDeleteButtonClicked) }
                )
            }
        ) {
            OrderFormNavGraph(
                navController = navController,
                popBack = { navController.navigateUp() },
                vm = vm
            )
            it
        }
    }
}

@Composable
fun OrderFormNavGraph(
    navController: NavHostController,
    popBack: () -> Unit = {},
    vm: OrderFormViewModel,
) {
    val uiState by vm.uiState.collectAsState()

    NavHost(
        route = OrderFormRoute.ORDER_FORM_ROOT.route,
        modifier = Modifier
            .padding(top = 60.dp)
            .fillMaxSize()
            .background(White),
        navController = navController,
        startDestination = OrderFormRoute.ORDER_FORM_MAIN.route,
    ) {

        composable(
            route = OrderFormRoute.ORDER_FORM_MAIN.route
        ) {
            val cardNavMap = mapOf(
                OrderFormUiSideEffect.NavigateToDateTimeScreen to {
                    navController.navigate(OrderFormRoute.DATE_TIME_SELECTION.route)
                },
                OrderFormUiSideEffect.NavigateToLocationScreen to {
                    navController.navigate(OrderFormRoute.LOCATION_SELECTION.route)
                },
                OrderFormUiSideEffect.NavigateToVehicleScreen to {
                    navController.navigate(OrderFormRoute.VEHICLE_SELECTION.route)
                },
                OrderFormUiSideEffect.NavigateToLoadDetailScreen to {
                    navController.navigate(OrderFormRoute.LOAD_DETAIL.route)
                },
                OrderFormUiSideEffect.NavigateToServiceOptionScreen to {
                    navController.navigate(OrderFormRoute.SERVICE_OPTION_SELECTION.route)
                },
                OrderFormUiSideEffect.NavigateToPaySelectionScreen to {
                    navController.navigate(OrderFormRoute.PAY_CHECK.route)
                },
            )
            OrderFormMainScreen(cardNavMap = cardNavMap, vm = vm)
        }

        composable(
            route = OrderFormRoute.DATE_TIME_SELECTION.route
        ) {
            DateTimeScreen(
                dateTime = uiState.dateTime,
                onDateChanged = { vm.setEvent(OrderFormUiEvent.OnDateSelected(it)) },
                onTimeChanged = { vm.setEvent(OrderFormUiEvent.OnTimeSelected(it)) },
                onInputCompleted = { vm.onInputCompleted(CardType.DATETIME, popBack) },
            )
        }

        composable(
            route = OrderFormRoute.LOCATION_SELECTION.route
        ) {
            LocationScreen(
                locations = uiState.locations,
                onDepartChanged = { vm.setEvent(OrderFormUiEvent.OnDepartSelected(it)) },
                onArriveChanged = { vm.setEvent(OrderFormUiEvent.OnArriveSelected(it)) },
                onInputCompleted = { vm.onInputCompleted(CardType.LOCATION, popBack) },
            )
        }

        composable(
            route = OrderFormRoute.VEHICLE_SELECTION.route
        ) {
            VehicleScreen(
                vehicleOptions = uiState.vehicleOptions,
                onVehicleTypeChanged = { vm.setEvent(OrderFormUiEvent.OnVehicleTypeSelected(it)) },
                onVehicleOptionChanged = { vm.setEvent(OrderFormUiEvent.OnVehicleOptionSelected(it)) },
                onInputCompleted = { vm.onInputCompleted(CardType.VEHICLE, popBack) }
            )
        }

        composable(
            route = OrderFormRoute.LOAD_DETAIL.route
        ) {
            LoadDetailScreen(
                loadDetail = uiState.loadDetail,
                onLoadDetailEntered = { vm.setEvent(OrderFormUiEvent.OnLoadDetailEntered(it)) },
                onInputCompleted = { vm.onInputCompleted(CardType.LOAD_DETAIL, popBack) }
            )
        }

        composable(
            route = OrderFormRoute.SERVICE_OPTION_SELECTION.route
        ) {
            ServiceOptionScreen(
                serviceOptions = uiState.serviceOptions,
                onServiceOptionSelected = { vm.setEvent(OrderFormUiEvent.OnServiceOptionSelected(it)) },
                onRideWithUnClicked = { vm.setEvent(OrderFormUiEvent.OnRideWithOptionClicked(false)) },
                onAgreement = { vm.setEvent(OrderFormUiEvent.OnRideWithOptionClicked(true)) },
                onInputCompleted = { vm.onInputCompleted(CardType.SERVICE_OPTION, popBack) },
            )
        }

        composable(OrderFormRoute.PAY_CHECK.route) {
            val context = LocalContext.current
            PayCheckScreen(
                locations = uiState.locations,
                popBack = popBack,
                nextStep = { Toast.makeText(context, "주문 프로세스 완료!", Toast.LENGTH_SHORT).show() }
            )
        }
    }
}

enum class OrderFormRoute(val route: String) {
    ORDER_FORM_ROOT("order-form-root"),
    ORDER_FORM_MAIN("order-form-main"),
    DATE_TIME_SELECTION("date-time-selection"),
    LOCATION_SELECTION("location-selection"),
    VEHICLE_SELECTION("vehicle-selection"),
    LOAD_DETAIL("load-detail"),
    SERVICE_OPTION_SELECTION("service-option-selection"),
    PAY_CHECK("pay-check")
}
