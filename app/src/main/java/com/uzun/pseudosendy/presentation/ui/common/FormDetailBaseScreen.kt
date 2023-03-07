package com.uzun.pseudosendy.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uzun.pseudosendy.presentation._const.UIConst
import com.uzun.pseudosendy.presentation.ui.orderform.main.CardType
import com.uzun.pseudosendy.ui.theme.DayBorderDefault
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme

@Composable
fun FormDetailBaseScreen(
    cardType: CardType,
    modifier: Modifier = Modifier,
    arrangement : Arrangement.Vertical = Arrangement.Top,
    onButtonClicked: () -> Unit,
    content: LazyListScope.() -> Unit,
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(horizontal = UIConst.SPACE_XL)
    ) {
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = arrangement
        ) {
            typeInformation(cardType)
            lineSpacer()
            content()
        }

        FloatingRoundBottomButton(
            text = "입력 완료하기",
            onClick = onButtonClicked
        )
    }
}

fun LazyListScope.typeInformation(cardType: CardType) = item {
    Box(Modifier.fillMaxWidth()) {
        TypeTextInformation(
            title = cardType.title,
            description = cardType.description,
            modifier = Modifier.align(Alignment.TopStart)
        )
        TypeIconInformation(
            iconList = cardType.defaultIconList,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

@Composable
private fun TypeTextInformation(
    title: String,
    description: String,
    modifier: Modifier,
) = Column(modifier) {
    Text(
        text = title,
        style = PseudoSendyTheme.typography.XLBold
    )
    Spacer(modifier = Modifier.size(UIConst.SPACE_XS))
    Text(
        text = description,
        style = PseudoSendyTheme.typography.Normal
    )
}

@Composable
private fun TypeIconInformation(
    iconList: List<Int>,
    modifier: Modifier,
) = Row(modifier) {
    iconList.forEach { resourceId ->
        Spacer(Modifier.size(UIConst.SPACE_XXS))
        Image(
            painterResource(id = resourceId),
            modifier = Modifier.size(48.dp),
            contentDescription = null
        )
    }
}

fun LazyListScope.lineSpacer() = item {
    Spacer(Modifier.size(UIConst.SPACE_XL))
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(DayBorderDefault))
    Spacer(Modifier.size(UIConst.SPACE_XL))
}

@Preview
@Composable
fun PreviewBaseScreen() {
    PseudoSendyTheme {
        FormDetailBaseScreen(cardType = CardType.DATETIME, onButtonClicked = {}) {

        }
    }
}