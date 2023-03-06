package com.uzun.pseudosendy

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun OrderFormScreen(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = OrderFormRoute.SPLASH.route
    ) {
        composable(
            route = OrderFormRoute.SPLASH.route
        ) {
            // TODO
        }

        composable(
            route = OrderFormRoute.ORDER_FORM_MAIN.route
        ) {
            // TODO
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
    SPLASH("splash"),
    ORDER_FORM_MAIN("order-form-main"),
    DATE_TIME_SELECTION("date-time-selection"),
    LOCATION_SELECTION("location-selection"),
    VEHICLE_SELECTION("vehicle-selection"),
    LOAD_DETAIL("load-detail"),
    SERVICE_OPTION_SELECTION("service-option-selection")
}