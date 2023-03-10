package com.uzun.pseudosendy.presentation.ui.orderform.location

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.uzun.pseudosendy.presentation._const.UIConst
import com.uzun.pseudosendy.presentation.model.Location
import com.uzun.pseudosendy.presentation.ui.common.RoundInputField
import com.uzun.pseudosendy.ui.theme.DayGrayscale400
import com.uzun.pseudosendy.ui.theme.ImagePlaceholder
import com.uzun.pseudosendy.ui.theme.PseudoSendyTheme

@Preview
@Composable
fun LocationSearchSheetScreen(
    searchMode: Boolean = true,
    onHidden: () -> Unit = {},
    onLocationSelected: (Location) -> Unit = {},
    viewModel: LocationViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        var text by remember { mutableStateOf("") }

        SheetHeaderText(searchMode = searchMode)

        QueryInputField(
            text = text,
            onValueChanged = { text = it },
            searchLocation = viewModel::getSearchedLocationList
        )

        Spacer(
            modifier = Modifier
                .padding(top = UIConst.SPACE_M)
                .fillMaxWidth()
                .height(1.dp)
                .background(ImagePlaceholder)
        )

        LazyColumn(
            Modifier
                .padding(UIConst.SPACE_S)
                .wrapContentHeight()
        ) {
            item {
                Text(
                    "주소 목록",
                    style = PseudoSendyTheme.typography.NormalBold,
                    modifier = Modifier.padding(bottom = UIConst.SPACE_S).padding(start = UIConst.SPACE_S)
                )
            }

            items(items = viewModel.addrList.list) {
                Column(
                    Modifier
                        .clickable {
                            onLocationSelected(it)
                            onHidden()
                            viewModel.resetAddrList()
                            text = ""
                        }
                        .padding(horizontal = UIConst.SPACE_XS)
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Text(it.roadAddr, style=PseudoSendyTheme.typography.XXS)
                    Text(it.jibunAddr, style=PseudoSendyTheme.typography.XXS)
                }
                Spacer(
                    modifier = Modifier
                        .padding(vertical = UIConst.SPACE_S)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(ImagePlaceholder)
                )
            }
        }

    }
}

@Composable
fun SheetHeaderText(searchMode: Boolean) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .padding(UIConst.SPACE_M)
    ,
    verticalAlignment = Alignment.CenterVertically
) {
    Text(
        text = (if (searchMode) "출발" else "도착") + "지 주소 검색하기",
        style = PseudoSendyTheme.typography.MediumBold
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun QueryInputField(
    text: String,
    onValueChanged: (String) -> Unit,
    searchLocation: (String) -> Unit,
) =
    RoundInputField(
        Modifier
            .padding(horizontal = UIConst.SPACE_S)
            .fillMaxWidth()
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current

        BasicTextField(
            value = text,
            onValueChange = onValueChanged,
            modifier = Modifier.fillMaxWidth(),
            textStyle = PseudoSendyTheme.typography.Small,
            decorationBox = { innerTextField ->
                if (text.isBlank())
                    Text(
                        text = "지번 또는 도로명으로 주소 검색",
                        style = PseudoSendyTheme.typography.Small,
                        color = DayGrayscale400
                    )
                innerTextField()
            },
            keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    searchLocation(text)
                    keyboardController?.hide()
                })
        )
    }
