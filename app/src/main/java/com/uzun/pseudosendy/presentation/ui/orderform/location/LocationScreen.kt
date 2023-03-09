package com.uzun.pseudosendy.presentation.ui.orderform.location

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.uzun.pseudosendy.R
import com.uzun.pseudosendy.presentation._const.UIConst
import com.uzun.pseudosendy.presentation.model.Locations
import com.uzun.pseudosendy.presentation.model._enum.CardType
import com.uzun.pseudosendy.presentation.ui.common.FormDetailBaseScreen
import com.uzun.pseudosendy.presentation.ui.common.RoundInputField
import com.uzun.pseudosendy.ui.theme.DayBlueBase
import com.uzun.pseudosendy.ui.theme.DayGrayscale400
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme

@Preview
@Composable
fun LocationScreen(
    locations: Locations = Locations(),
    onDepartChanged: (String) -> Unit = {},
    onArriveChanged: (String) -> Unit = {},
    onWayPointAdded: (String) -> Unit = {},
    onInputCompleted: () -> Unit = {},
) = FormDetailBaseScreen(
    cardType = CardType.LOCATION,
    arrangement = Arrangement.spacedBy(UIConst.SPACE_XS),
    onButtonClicked = onInputCompleted,
) {
    guidePart(onInputCompleted)
    departField(
        location = locations.depart,
        onClick = { }
    )
    arriveField(
        location = locations.arrive,
        onClick = { }
    )
}

fun LazyListScope.guidePart(onClick: () -> Unit) = item {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GuideText()
        AddWayPoint(onClick = onClick)
    }
}

@Composable
fun GuideText() = Text(
    text = "출/도착지 주소를 알려주세요.",
    style = PseudoSendyTheme.typography.Small
)

@Composable
fun AddWayPoint(onClick: () -> Unit) = Row(
    modifier = Modifier.clickable(onClick = onClick),
    verticalAlignment = Alignment.CenterVertically
) {
    Image(painterResource(id = R.drawable.ic_add_square), contentDescription = null)
    Spacer(Modifier.size(UIConst.SPACE_XXS))
    Text(
        text = "경유지 추가",
        style = PseudoSendyTheme.typography.XXS,
        color = DayBlueBase
    )
}

fun LazyListScope.departField(
    location : String,
    onClick: () -> Unit
) = item {
    RoundInputField(
        onClick = onClick,
        content = {
            IconWithGreyText(R.drawable.ic_depart_solid, location.ifBlank {"출발지 주소 입력하기"})
        }
    )
}

fun LazyListScope.arriveField(
    location : String,
    onClick: () -> Unit
) = item {
    RoundInputField(
        onClick = onClick,
        content = { IconWithGreyText(R.drawable.ic_arrive_solid, location.ifBlank {"도착지 주소 입력하기"}) }
    )
}

@Composable
fun IconWithGreyText(
    iconId: Int,
    text: String,
) {
    Icon(
        painterResource(id = iconId),
        contentDescription = null,
    )
    Spacer(modifier = Modifier.size(UIConst.SPACE_XXS))
    Text(
        text = text,
        style = PseudoSendyTheme.typography.Small,
        color = DayGrayscale400
    )
}

