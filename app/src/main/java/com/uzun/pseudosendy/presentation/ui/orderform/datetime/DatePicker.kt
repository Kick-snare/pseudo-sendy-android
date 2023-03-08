package com.uzun.pseudosendy.presentation.ui.orderform.datetime

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DatePicker(
    value: String,
    onValueChange: (String) -> Unit = {},
    pattern: String = "yyyy-MM-dd",
    content: @Composable RowScope.(String, () -> Unit) -> Unit,
) {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val date = if (value.isNotBlank()) LocalDate.parse(value, formatter) else LocalDate.now()
    val dialog = DatePickerDialog(
        LocalContext.current,
        { _, year, month, dayOfMonth ->
            onValueChange( LocalDate.of(year, month + 1, dayOfMonth).toString() )
        },
        date.year,
        date.monthValue - 1,
        date.dayOfMonth,
    )
    val formattedDate by lazy {
        val ymd = value.split("-")
        runCatching { "" + ymd[0] + "년 " + ymd[1] + "월 " + ymd[2] + "일" }.getOrElse { "" }
    }
    Row(){ content(formattedDate, dialog::show) }
}