package com.uzun.pseudosendy.presentation.ui.paycheck

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.NaverMap
import com.uzun.pseudosendy.R
import com.uzun.pseudosendy.presentation._const.UIConst
import com.uzun.pseudosendy.presentation.ui.common.BaseRoundedButton
import com.uzun.pseudosendy.presentation.ui.common.ButtonSize
import com.uzun.pseudosendy.presentation.ui.common.RoundedPrimaryButton
import com.uzun.pseudosendy.ui.theme.*

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun PayCheckScreen(
    popBack: () -> Unit = {},
    nextStep: () -> Unit = {}
) {
    Box(Modifier.fillMaxSize()) {

        FloatingInfoCard()
        NaverMap(modifier = Modifier.zIndex(-1F))
        BottomPaySelectionSheet(popBack, nextStep)
    }
}

@Composable
fun BoxScope.FloatingInfoCard() {
    Surface(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(top = 20.dp)
            .size(161.dp, 61.dp)
            .background(White, RoundedCornerShape(12.dp))
        ,
        shadowElevation = 5.dp
    ) {
        Row(
            modifier = Modifier.padding(UIConst.SPACE_S),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextWithColumnLabel("이동거리" ,"14km")
            Spacer(
                Modifier
                    .fillMaxHeight(0.5f)
                    .width(1.dp)
                    .background(ImagePlaceholder))
            TextWithColumnLabel("운송요금" ,"80,000원", true)
        }
    }
}

@Composable
fun TextWithColumnLabel(
    label: String,
    text: String,
    isRTL : Boolean = false
) = Column(
    horizontalAlignment = if(!isRTL) Alignment.Start else Alignment.End
) {
    Text(
        text = label,
        style = PseudoSendyTheme.typography.XXS
    )
    Text(
        text = text,
        style = PseudoSendyTheme.typography.SmallBold
    )
}

@Composable
fun BoxScope.BottomPaySelectionSheet(
    popBack: () -> Unit = {},
    nextStep: () -> Unit = {}
) {
    Surface(
        shape = RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp),
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .defaultMinSize(minHeight = 218.dp)
            .background(White, RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp))

    ) {
        Column(
            Modifier.padding(UIConst.SPACE_XL),
            verticalArrangement = Arrangement.spacedBy(UIConst.SPACE_S)
        ) {
            PaySelectionHead()
            PaySelectionContent()
            NavigatingButtons(popBack, nextStep)
        }
    }
}

@Composable
fun PaySelectionHead() = Row(
    modifier = Modifier.fillMaxWidth().padding(bottom = UIConst.SPACE_XXS),
    horizontalArrangement = Arrangement.SpaceBetween
) {
    Text(
        text = "결제 방법",
        style = PseudoSendyTheme.typography.NormalBold
    )
    Row(verticalAlignment = Alignment.CenterVertically,) {
        Image(
            painterResource(id = R.drawable.ic_tooltip),
            contentDescription = null
        )
        Text(
            text = "센디 이사 정책 안내",
            style = PseudoSendyTheme.typography.Small,
            color = DayBlueBase
        )
    }
}

@Composable
fun PaySelectionContent() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(UIConst.SPACE_XS)
    ){
        Image(
            painterResource(id = R.drawable.radio_blue_c),
            contentDescription = null
        )
        Text(
            text = "결제하기",
            style = PseudoSendyTheme.typography.Normal.copy(color = Color.Black)
        )
        Text(
            text = "- 선택한 차량으로 예약됩니다.",
            style = PseudoSendyTheme.typography.Small.copy(color = DayGrayscale300)
        )
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(UIConst.SPACE_XS)
    ){
        Image(
            painterResource(id = R.drawable.radio_uncheck),
            contentDescription = null
        )
        Text(
            text = "결제하기",
            style = PseudoSendyTheme.typography.Normal.copy(color = Color.Black)
        )
        Text(
            text = "- 상담 후 예약이 확정됩니다.",
            style = PseudoSendyTheme.typography.Small.copy(color = DayGrayscale300)
        )
    }
}


@Composable
fun NavigatingButtons(
    popBack: () -> Unit = {},
    nextStep: () -> Unit = {}
) = Row(
    modifier = Modifier.fillMaxWidth().padding(top = UIConst.SPACE_S),
    horizontalArrangement = Arrangement.spacedBy(UIConst.SPACE_S)
)  {
    BaseRoundedButton(
        onClick = popBack,
        type = ButtonSize.NORMAL,
        colors = ButtonDefaults.buttonColors(
            contentColor = Black,
            backgroundColor = White
        ),
        modifier = Modifier.width(80.dp)
    ) {
        Text(
            text = "이전",
            style = PseudoSendyTheme.typography.Normal
        )
    }
    RoundedPrimaryButton(onClick = nextStep) {
        Text(
            text = "다음 단계로",
            style = PseudoSendyTheme.typography.Normal.copy(color = White)
        )
    }
}
