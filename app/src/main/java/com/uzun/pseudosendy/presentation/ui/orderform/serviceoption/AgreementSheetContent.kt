package com.uzun.pseudosendy.presentation.ui.orderform.serviceoption

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uzun.pseudosendy.R
import com.uzun.pseudosendy.presentation._const.UIConst
import com.uzun.pseudosendy.presentation.ui.common.RoundedPrimaryButton
import com.uzun.pseudosendy.ui.theme.DayBlueBase
import com.uzun.pseudosendy.ui.theme.DayBorderDefault
import com.uzun.pseudosendy.ui.theme.DayGrayscale100
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme

@Composable
fun AgreementSheetContent(
    hideBottomSheet: () -> Unit,
    onAgreement: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .padding(UIConst.SPACE_XL)
            .defaultMinSize(minHeight = 400.dp)
            .fillMaxWidth()
    ) {
        BottomSheetHeader(hideBottomSheet)
        LineSpacer()
        RideFeeGuide()
        LineSpacer()
        AgreementButton(hideBottomSheet, onAgreement)
    }
}

@Composable
fun BottomSheetHeader(hideBottomSheet: () -> Unit) = Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween
) {
    Text(
        text = "동승 서비스를 이용할 때\n꼭 확인해주세요",
        style = PseudoSendyTheme.typography.LargeBold
    )
    IconButton(onClick = hideBottomSheet) {
        Icon(
            painter = painterResource(R.drawable.icon_solid_close),
            contentDescription = null
        )
    }
}

@Composable
fun LineSpacer() {
    Spacer(Modifier.size(UIConst.SPACE_XL))
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(DayBorderDefault)
    )
    Spacer(Modifier.size(UIConst.SPACE_XL))
}

@Composable
fun RideFeeGuide() {
    NonLineBrokenText(text = "동승비는 현장에서 직접 기사님에게 지불해주세요. 동승은 1명만 가능합니다.")
    RideFeeField()
    NonLineBrokenText(text = "반려동물과 함께 탑승할 경우 안전을 위해 꼭 케이지를 이용해야하며, 사전에 센디와 협의되지 않은 반려동물 동승은 탑승이 제한됩니다.")
}

@Composable
fun NonLineBrokenText(text: String) = Text(
    text = text.replace(" ", "\u00A0"),
    style = PseudoSendyTheme.typography.Normal,
    color = DayGrayscale100
)

@Composable
fun RideFeeField() = Row(
    modifier = Modifier
        .padding(vertical = UIConst.SPACE_M)
        .background(
            Color(0xFFf0f3ff),
            RoundedCornerShape(UIConst.BUTTON_RADIUS_NORMAL)
        )
        .fillMaxWidth()
        .padding(UIConst.SPACE_M),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    Text(
        text = "동승비",
        style = PseudoSendyTheme.typography.NormalBold,
        color = DayBlueBase
    )
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "3,000원",
            style = PseudoSendyTheme.typography.MediumBold,
            color = DayBlueBase
        )
        Spacer(Modifier.size(UIConst.SPACE_XXS))
        Image(
            painter = painterResource(id = R.drawable.icon_solid_coin),
            contentDescription = null
        )
    }
}

@Composable
fun AgreementButton(
    hideBottomSheet: () -> Unit,
    onAgreement: () -> Unit = {},
) = RoundedPrimaryButton(
    onClick = {
        onAgreement()
        hideBottomSheet()
    }
) {
    Text(
        text = "동의하기",
        style = PseudoSendyTheme.typography.Normal,
        color = Color.White
    )
}
