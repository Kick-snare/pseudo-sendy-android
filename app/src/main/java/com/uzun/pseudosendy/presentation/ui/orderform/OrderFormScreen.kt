package com.uzun.pseudosendy.presentation.ui.orderform

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uzun.pseudosendy.R
import com.uzun.pseudosendy.presentation._const.UIConst.SPACE_XS
import com.uzun.pseudosendy.presentation.ui.orderform.OrderFormContract.OrderFormUiEvent
import com.uzun.pseudosendy.presentation.ui.orderform.OrderFormContract.OrderFormUiSideEffect
import com.uzun.pseudosendy.presentation.ui.orderform.datetime.DateTimeScreen
import com.uzun.pseudosendy.presentation.ui.orderform.main.OrderFormMainScreen
import com.uzun.pseudosendy.ui.theme.White

@Composable
fun OrderFormScreen(
    navController: NavHostController = rememberNavController(),
    vm: OrderFormViewModel = hiltViewModel(),
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Spacer(Modifier.size(SPACE_XS))
        OrderFormTopBar(
            onClickBackButton = { navController.popBackStack() },
            onClickDeleteButton = { vm.setEvent(OrderFormUiEvent.OnDeleteButtonClicked) }
        )
        OrderFormNavGraph(navController)
    }
}

@Composable
fun OrderFormTopBar(
    onClickBackButton: () -> Unit,
    onClickDeleteButton: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = SPACE_XS)
            .padding(vertical = 11.dp)
    ) {
        TopBackIconButton(onClick = onClickBackButton)
        TopDeleteIconButton(onClick = onClickDeleteButton)
    }
}

@Composable
fun BoxScope.TopBackIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier.align(Alignment.CenterStart),
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back_24),
            contentDescription = "back button"
        )
    }
}

@Composable
fun BoxScope.TopDeleteIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier.align(Alignment.CenterEnd),
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_delete_bin),
            contentDescription = "delete written form"
        )
    }
}

@Composable
fun OrderFormNavGraph(navController: NavHostController) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = OrderFormRoute.ORDER_FORM_MAIN.route
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
                }
            )
            OrderFormMainScreen(cardNavMap = cardNavMap)
        }

        composable(
            route = OrderFormRoute.DATE_TIME_SELECTION.route
        ) {
            DateTimeScreen()
        }

        composable(
            route = OrderFormRoute.LOCATION_SELECTION.route
        ) {
            Text("LOCATION_SELECTION")
        }

        composable(
            route = OrderFormRoute.VEHICLE_SELECTION.route
        ) {
            Text("VEHICLE_SELECTION")
        }

        composable(
            route = OrderFormRoute.LOAD_DETAIL.route
        ) {
            Text("LOAD_DETAIL")
        }

        composable(
            route = OrderFormRoute.SERVICE_OPTION_SELECTION.route
        ) {
            Text("SERVICE_OPTION_SELECTION")
        }
    }
}

enum class OrderFormRoute(val route: String) {
    ORDER_FORM_MAIN("order-form-main"),
    DATE_TIME_SELECTION("date-time-selection"),
    LOCATION_SELECTION("location-selection"),
    VEHICLE_SELECTION("vehicle-selection"),
    LOAD_DETAIL("load-detail"),
    SERVICE_OPTION_SELECTION("service-option-selection")
}