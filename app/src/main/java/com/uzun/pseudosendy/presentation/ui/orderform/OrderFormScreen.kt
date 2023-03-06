package com.uzun.pseudosendy.presentation.ui.orderform

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uzun.pseudosendy.R
import com.uzun.pseudosendy.presentation._const.UIConst.SPACE_M
import com.uzun.pseudosendy.presentation.ui.orderform.main.OrderFormMainScreen
import com.uzun.pseudosendy.ui.theme.White

@Composable
fun OrderFormScreen(
    navController: NavHostController = rememberNavController(),
) {
    Column(Modifier.fillMaxSize().background(White)) {
        OrderFormTopBar()
        OrderFormNavGraph(navController)
    }
}

@Preview
@Composable
fun OrderFormTopBar() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = SPACE_M)
            .padding(vertical = 11.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back_24),
            contentDescription = "back button"
        )
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
            OrderFormMainScreen()
        }

        composable(
            route = OrderFormRoute.DATE_TIME_SELECTION.route
        ) {
            // TODO
        }

        composable(
            route = OrderFormRoute.LOCATION_SELECTION.route
        ) {
            // TODO
        }

        composable(
            route = OrderFormRoute.VEHICLE_SELECTION.route
        ) {
            // TODO
        }

        composable(
            route = OrderFormRoute.LOAD_DETAIL.route
        ) {
            // TODO
        }

        composable(
            route = OrderFormRoute.SERVICE_OPTION_SELECTION.route
        ) {
            // TODO
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