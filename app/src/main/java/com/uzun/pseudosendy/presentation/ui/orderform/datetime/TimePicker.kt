package com.uzun.pseudosendy.presentation.ui.orderform.datetime

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun TimePicker(
    value: String,
    onValueChange: (String) -> Unit = {},
    content: @Composable RowScope.(String, () -> Unit) -> Unit,
) {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    val time = if (value.isNotBlank()) LocalTime.parse(value, formatter) else LocalTime.now()
    val mTime = remember { mutableStateOf("") }

    val dialog = TimePickerDialog(
        LocalContext.current,
        { _, mHour: Int, mMinute: Int ->
            onValueChange(LocalTime.of(mHour, mMinute).toString())
        },
        time.hour,
        time.minute,
        true,
    )

    mTime.value =
        if(time.hour > 12) "오후 ${time.hour-12} 시 ${time.minute} 분"
        else "오전 ${time.hour} 시 ${time.minute} 분"

    Row(){ content(if(value.isNotBlank()) mTime.value else "", dialog::show) }
}