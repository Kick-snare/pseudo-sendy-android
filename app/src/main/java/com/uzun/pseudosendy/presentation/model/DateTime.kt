package com.uzun.pseudosendy.presentation.model

data class DateTime(
    val date: String = "",
    val time: String = ""
) : FormData {
    override fun isCompleted(): Boolean = date.isNotBlank() && time.isNotBlank()
}
