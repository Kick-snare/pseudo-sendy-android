package com.uzun.pseudosendy.presentation.ui.orderform.loaddetail

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uzun.pseudosendy.presentation._const.UIConst
import com.uzun.pseudosendy.presentation.ui.common.FormDetailBaseScreen
import com.uzun.pseudosendy.presentation.ui.common.RoundInputField
import com.uzun.pseudosendy.presentation.ui.orderform.main.CardType
import com.uzun.pseudosendy.ui.theme.DayGrayscale300
import com.uzun.pseudosendy.ui.theme.DayGrayscale400
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme

@Preview
@Composable
fun LoadDetailScreen() {
    var localFocusManager = LocalFocusManager.current
    var text by remember { mutableStateOf("") }

    FormDetailBaseScreen(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = { localFocusManager.clearFocus() })
        },
        cardType = CardType.LOAD_DETAIL,
        arrangement = Arrangement.spacedBy(UIConst.SPACE_XS),
        onButtonClicked = {},
    ) {
        upperGuideText()
        textAreaField(text) { text = it }
        bottomGuideText()
    }
}

fun LazyListScope.upperGuideText() = item {
    Text(
        text = "어떤 짐을 옮기시나요?",
        style = PseudoSendyTheme.typography.Small
    )
}

fun LazyListScope.bottomGuideText() = item {
    Text(
        text = "- 파손이나 분실 시 빠른 보상처리를 위해 운송물품을 상세히 입력하세요\n" +
            "- 예시) 5단 책장 3개, 조립식 가구가 적재 된 파렛트 3개",
        style = PseudoSendyTheme.typography.XS,
        color = DayGrayscale300
    )
}

@OptIn(ExperimentalComposeUiApi::class)
fun LazyListScope.textAreaField(
    text: String = "",
    onValueChanged: (String) -> Unit = {},
) = item {
    RoundInputField {
        BasicTextField(
            value = text,
            onValueChange = onValueChanged,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            textStyle = PseudoSendyTheme.typography.Small,
            decorationBox = { innerTextField ->
                if (text.isBlank())
                    Text(
                        text = "짐에 관한 설명 및 주의사항 등을 적어주세요.",
                        style = PseudoSendyTheme.typography.Small,
                        color = DayGrayscale400
                    )
                innerTextField()
            }
        )
    }
}