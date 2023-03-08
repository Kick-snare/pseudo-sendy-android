package com.uzun.pseudosendy.presentation.ui.orderform.vehicle

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uzun.pseudosendy.R
import com.uzun.pseudosendy.presentation._const.UIConst
import com.uzun.pseudosendy.presentation.ui.common.DropDownMenuSelector
import com.uzun.pseudosendy.presentation.ui.common.FormDetailBaseScreen
import com.uzun.pseudosendy.presentation.ui.orderform.main.CardType
import com.uzun.pseudosendy.ui.theme.DayGrayscale100
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme


@Preview
@Composable
fun VehicleScreen(
    // hiltViewModel()
) = FormDetailBaseScreen(
    cardType = CardType.VEHICLE,
    arrangement = Arrangement.spacedBy(UIConst.SPACE_XS),
    onButtonClicked = {},
) {
    guideText()
    vehicleDropdown {}
    vehicleOptionDropdown {}
}

fun LazyListScope.guideText() = item {
    Text(
        text = "필요한 차량과 차량 옵션을 모두 선택해주세요.",
        style = PseudoSendyTheme.typography.Small
    )
}

fun LazyListScope.vehicleDropdown(onClick: () -> Unit) = item {
    DropDownMenuSelector(
        optionList =  listOf("1톤", "라보", "다마스", "2.5톤", "3.5톤", "5톤", "5톤+"),
        onItemClick = { selectedOption ->
            Log.e("test", "selected option -> $selectedOption")
        }
    )
}

fun LazyListScope.vehicleOptionDropdown(onClick: () -> Unit) = item {
    DropDownMenuSelector(
        optionList =  listOf("기본(카고)", "탑차", "리프트", "윙바디", "냉장", "냉동"),
        onItemClick = { selectedOption ->
            Log.e("test", "selected option -> $selectedOption")
        }
    )
}

@Composable
fun BoxScope.DropDownIconButton() = Icon(
    painterResource(id = R.drawable.baseline_arrow_drop_down_24),
    modifier = Modifier
        .size(16.dp)
        .align(Alignment.CenterEnd),
    contentDescription = null
)

@Composable
fun OptionTextField(
    text: String = ""
) = Text(
    text = text,
    style = PseudoSendyTheme.typography.Small,
    color = DayGrayscale100
)