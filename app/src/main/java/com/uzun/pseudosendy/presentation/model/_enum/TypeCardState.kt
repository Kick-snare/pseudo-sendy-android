package com.uzun.pseudosendy.presentation.model._enum

import androidx.compose.ui.graphics.Color
import com.uzun.pseudosendy.ui.theme.DayBorderDefault
import com.uzun.pseudosendy.ui.theme.DayRedL60

/*
enum class TypeCardState(val backgroundColor: Color, val borderColor: Color) {
    DEFAULT(Color.White, DayBorderDefault),
    COMPLETE(Color.White, DayBorderDefault),
    ERROR(Color(0xFFfffbfb), DayRedL60),
}
*/

sealed class TypeCardState(val backgroundColor: Color, val borderColor: Color) {
    object DEFAULT : TypeCardState(Color.White, DayBorderDefault)
    object FILLED : TypeCardState(Color.White, DayBorderDefault)
    data class ERROR(val message: String? = null) : TypeCardState(Color(0xFFfffbfb), DayRedL60)
}