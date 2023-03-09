package com.uzun.pseudosendy.presentation.ui.orderform.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uzun.pseudosendy.R
import com.uzun.pseudosendy.presentation._const.UIConst
import com.uzun.pseudosendy.presentation.model._enum.CardType
import com.uzun.pseudosendy.presentation.model._enum.TypeCardState
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme

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
            .border(1.dp, state.borderColor, RoundedCornerShape(UIConst.BUTTON_RADIUS_NORMAL))
            .fillMaxWidth()
            .background(state.backgroundColor, RoundedCornerShape(UIConst.BUTTON_RADIUS_NORMAL))
            .padding(vertical = UIConst.SPACE_M)
            .padding(horizontal = UIConst.SPACE_M),
    ) {
        OrderTypeCardHeader(type, state)
        content()
    }
}

@Composable
fun OrderTypeCardHeader(type: CardType, state: TypeCardState) =
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OrderTypeCardReadableArea(
            title = type.title,
            isCompleted = (state is TypeCardState.FILLED)
        )
        OrderTypeCardIconArea(iconList = type.getIconListByState(state))
    }

@Composable
fun OrderTypeCardReadableArea(title: String, isCompleted: Boolean) =
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = title, style = PseudoSendyTheme.typography.NormalBold)
        if (isCompleted) {
            Image(
                painterResource(id = R.drawable.check_blue_bold),
                contentDescription = null,
                modifier = Modifier.padding(start = UIConst.SPACE_XXS)
            )
        }
    }

@Composable
fun OrderTypeCardIconArea(iconList: List<Int>) =
    Row(verticalAlignment = Alignment.CenterVertically) {
        iconList.forEach { resourceId ->
            Spacer(Modifier.size(UIConst.SPACE_XXS))
            Image(painterResource(id = resourceId), contentDescription = null)
        }
        Spacer(Modifier.size(UIConst.SPACE_M))
        Image(
            painterResource(id = R.drawable.navigation_chevron_right_24),
            contentDescription = null
        )
    }

@Preview
@Composable
fun PreviewOrderTypeCards() =
    Column(
        Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OrderTypeCard(CardType.DATETIME, TypeCardState.FILLED)
        OrderTypeCard(CardType.DATETIME, TypeCardState.DEFAULT)
        OrderTypeCard(CardType.DATETIME, TypeCardState.ERROR(null))

        OrderTypeCard(CardType.VEHICLE, TypeCardState.FILLED)
        OrderTypeCard(CardType.VEHICLE, TypeCardState.DEFAULT)
        OrderTypeCard(CardType.VEHICLE, TypeCardState.ERROR(null))

        OrderTypeCard(CardType.SERVICE_OPTION, TypeCardState.FILLED)
        OrderTypeCard(CardType.SERVICE_OPTION, TypeCardState.DEFAULT)
        OrderTypeCard(CardType.SERVICE_OPTION, TypeCardState.ERROR(null))
    }