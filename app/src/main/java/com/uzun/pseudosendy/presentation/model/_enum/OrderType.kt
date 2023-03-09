package com.uzun.pseudosendy.presentation.model._enum

import com.uzun.pseudosendy.R

enum class CardType(
    val title: String,
    val description: String,
    val defaultIconList: List<Int>,
    val disabledIconList: List<Int>,
    val completeIconList: List<Int>,
    val errorIconList: List<Int>,
) {
    DATETIME(
        title = "운송일시",
        description = "언제 보낼까요?",
        defaultIconList = listOf(R.drawable.ic_date_number_in),
        disabledIconList = listOf(R.drawable.ic_calendar_in),
        completeIconList = listOf(R.drawable.ic_date_in_check),
        errorIconList = listOf(R.drawable.ic_calendar_in),
    ),
    LOCATION(
        title = "출·도착지",
        description = "어디로 보낼까요?",
        defaultIconList = listOf(R.drawable.ic_loc_circle_black),
        disabledIconList = listOf(R.drawable.ic_gps_in),
        completeIconList = listOf(R.drawable.ic_loc_in_check),
        errorIconList = listOf(R.drawable.ic_gps_in),
    ),
    VEHICLE(
        title = "짐을 옮길 차량",
        description = "어떤 차량으로 옮기시나요?",
        defaultIconList = listOf(
            R.drawable.ic_truck_circle_black,
            R.drawable.ic_weight_circle_black
        ),
        disabledIconList = listOf(R.drawable.ic_vehicle_in, R.drawable.ic_weight_in),
        completeIconList = listOf(R.drawable.ic_truck_in_check, R.drawable.ic_weight_in_check),
        errorIconList = listOf(R.drawable.ic_vehicle_in, R.drawable.ic_weight_in),
    ),
    LOAD_DETAIL(
        title = "옮기실 물품 정보",
        description = "짐 정보를 입력해주세요.",
        defaultIconList = listOf(R.drawable.ic_box_circle_black),
        disabledIconList = listOf(R.drawable.ic_load_in),
        completeIconList = listOf(R.drawable.icon_box_in_check),
        errorIconList = listOf(R.drawable.ic_load_in),
    ),
    SERVICE_OPTION(
        title = "서비스 옵션 선택",
        description = "기타 옵션을 선택해주세요.",
        defaultIconList = listOf(
            R.drawable.ic_carrying_circle_black,
            R.drawable.ic_carseat_circle_black
        ),
        disabledIconList = listOf(R.drawable.ic_load_help_in, R.drawable.ic_ride_with_in),
        completeIconList = listOf(
            R.drawable.ic_carrying_service_in_check,
            R.drawable.ic_car_ride_in_check
        ),
        errorIconList = listOf(R.drawable.ic_load_help_in, R.drawable.ic_ride_with_in),
    );

    fun getIconListByState(state: TypeCardState) =
        when (state) {
            TypeCardState.DEFAULT -> this.disabledIconList
            is TypeCardState.ERROR -> this.errorIconList
            is TypeCardState.FILLED -> this.completeIconList
        }
}