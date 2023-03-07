package com.uzun.pseudosendy.presentation.ui.orderform.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uzun.pseudosendy.R
import com.uzun.pseudosendy.presentation._const.UIConst.BUTTON_RADIUS_NORMAL
import com.uzun.pseudosendy.presentation._const.UIConst.SPACE_M
import com.uzun.pseudosendy.presentation._const.UIConst.SPACE_S
import com.uzun.pseudosendy.presentation._const.UIConst.SPACE_XXS
import com.uzun.pseudosendy.ui.theme.DayBorderDefault
import com.uzun.pseudosendy.ui.theme.DayRedL60
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme

enum class TypeCardState(val backgroundColor: Color, val borderColor: Color) {
    DEFAULT(Color.White, DayBorderDefault),
    COMPLETE(Color.White, DayBorderDefault),
    ERROR(Color(0xFFfffbfb), DayRedL60),
}

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
        defaultIconList = listOf(R.drawable.ic_truck_circle_black, R.drawable.ic_weight_circle_black),
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
            TypeCardState.COMPLETE -> this.completeIconList
            TypeCardState.ERROR -> this.errorIconList
        }
}

@Composable
fun OrderTypeCard(
    type: CardType,
    state: TypeCardState = TypeCardState.DEFAULT,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .background(state.backgroundColor, RoundedCornerShape(BUTTON_RADIUS_NORMAL))
            .border(1.dp, state.borderColor, RoundedCornerShape(BUTTON_RADIUS_NORMAL))
            .padding(horizontal = SPACE_M)
            .padding(vertical = SPACE_S)
        ,
    ) {
        OrderTypeCardHeader(type, state)
        content()
    }
}

@Composable
fun ColumnScope.OrderTypeCardHeader(type: CardType, state: TypeCardState) =
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OrderTypeCardReadableArea(
            title = type.title,
            isCompleted = (state == TypeCardState.COMPLETE)
        )
        OrderTypeCardIconArea(iconList = type.getIconListByState(state))
    }

@Composable
fun OrderTypeCardReadableArea(title: String, isCompleted: Boolean) =
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = title, style = PseudoSendyTheme.typography.NormalBold)
        if (isCompleted) {
            Icon(
                painterResource(id = R.drawable.check_blue_bold),
                contentDescription = null,
            )
        }
    }

@Composable
fun OrderTypeCardIconArea(iconList: List<Int>) =
    Row(verticalAlignment = Alignment.CenterVertically) {
        iconList.forEach { resourceId ->
            Spacer(Modifier.size(SPACE_XXS))
            Image(painterResource(id = resourceId), contentDescription = null)
        }
        Spacer(Modifier.size(SPACE_M))
        Icon(painterResource(id = R.drawable.navigation_chevron_right_24), contentDescription = null)
    }

@Preview
@Composable
fun PreviewOrderTypeCards() =
    Column(
        Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OrderTypeCard(CardType.DATETIME, TypeCardState.COMPLETE)
        OrderTypeCard(CardType.DATETIME, TypeCardState.DEFAULT)
        OrderTypeCard(CardType.DATETIME, TypeCardState.ERROR)

        OrderTypeCard(CardType.VEHICLE, TypeCardState.COMPLETE)
        OrderTypeCard(CardType.VEHICLE, TypeCardState.DEFAULT)
        OrderTypeCard(CardType.VEHICLE, TypeCardState.ERROR)

        OrderTypeCard(CardType.SERVICE_OPTION, TypeCardState.COMPLETE)
        OrderTypeCard(CardType.SERVICE_OPTION, TypeCardState.DEFAULT)
        OrderTypeCard(CardType.SERVICE_OPTION, TypeCardState.ERROR)
    }
