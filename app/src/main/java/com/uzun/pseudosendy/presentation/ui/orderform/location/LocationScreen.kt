package com.uzun.pseudosendy.presentation.ui.orderform.location

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uzun.pseudosendy.R
import com.uzun.pseudosendy.presentation._const.UIConst
import com.uzun.pseudosendy.presentation.model.Location
import com.uzun.pseudosendy.presentation.model.Locations
import com.uzun.pseudosendy.presentation.model._enum.CardType
import com.uzun.pseudosendy.presentation.ui.common.FormDetailBaseScreen
import com.uzun.pseudosendy.presentation.ui.common.ModalBottomSheet
import com.uzun.pseudosendy.presentation.ui.common.RoundInputField
import com.uzun.pseudosendy.ui.theme.DayBlueBase
import com.uzun.pseudosendy.ui.theme.DayGrayscale100
import com.uzun.pseudosendy.ui.theme.DayGrayscale400
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme

@Composable
fun LocationScreen(
    locations: Locations = Locations(),
    onDepartChanged: (Location) -> Unit = {},
    onArriveChanged: (Location) -> Unit = {},
    onWayPointAdded: (Location) -> Unit = {},
    onInputCompleted: () -> Unit = {},
) {
    var searchMode by remember { mutableStateOf(true) }
    ModalBottomSheet(
        sheetElevation = 0.dp,
        sheetShape = RectangleShape,
        activityContentScope = { onExpanded ->
            FormDetailBaseScreen(
                cardType = CardType.LOCATION,
                arrangement = Arrangement.spacedBy(UIConst.SPACE_XS),
                onButtonClicked = onInputCompleted,
            ) {
                guidePart(onInputCompleted)
                departField(
                    location = locations.depart,
                    onClick = {
                        onExpanded()
                        searchMode = true
                    }
                )
                arriveField(
                    location = locations.arrive,
                    onClick = {
                        onExpanded()
                        searchMode = false
                    }
                )
            }
        },
        sheetContent = { onHidden ->
            LocationSearchSheetScreen(
                searchMode = searchMode,
                onHidden = onHidden,
                onLocationSelected = { location ->
                    if (searchMode) onDepartChanged(location)
                    else onArriveChanged(location)
                }
            )
        }
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
    location: Location,
    onClick: () -> Unit,
) = item {
    RoundInputField(
        onClick = onClick,
        content = {
            IconWithText(
                R.drawable.ic_depart_solid,
                location.roadAddr.ifBlank { "출발지 주소 입력하기" },
                if (location.roadAddr.isNotBlank()) DayGrayscale100 else DayGrayscale400
            )
        }
    )
}

fun LazyListScope.arriveField(
    location: Location,
    onClick: () -> Unit,
) = item {
    RoundInputField(
        onClick = onClick,
        content = {
            IconWithText(
                R.drawable.ic_arrive_solid,
                location.roadAddr.ifBlank { "도착지 주소 입력하기" },
                if (location.roadAddr.isNotBlank()) DayGrayscale100 else DayGrayscale400
            )
        }
    )
}

@Composable
fun IconWithText(
    iconId: Int,
    text: String,
    color: Color = DayGrayscale400,
) {
    Icon(
        painterResource(id = iconId),
        contentDescription = null,
    )
    Spacer(modifier = Modifier.size(UIConst.SPACE_XXS))
    Text(
        text = text,
        style = PseudoSendyTheme.typography.Small,
        color = color
    )
}

