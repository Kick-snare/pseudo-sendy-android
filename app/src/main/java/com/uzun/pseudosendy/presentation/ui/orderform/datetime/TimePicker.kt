package com.uzun.pseudosendy.presentation.ui.orderform.datetime

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.time.LocalTime

@Composable
fun TimePicker(
    onValueChange: (String) -> Unit = {},
    content: @Composable RowScope.(String, () -> Unit) -> Unit,
) {
    val mTime = remember { mutableStateOf("") }
    val time = LocalTime.now()

    val dialog = TimePickerDialog(
        LocalContext.current,
        { _, mHour: Int, mMinute: Int ->
            onValueChange(LocalTime.of(mHour, mMinute).toString())
            mTime.value = if(mHour > 12) "오후 ${mHour-12} 시 $mMinute 분" else "오전 $mHour 시 $mMinute 분"
        },
        time.hour,
        time.minute,
        false,
    )
    Row(){ content(mTime.value, dialog::show) }
}