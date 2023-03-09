package com.uzun.pseudosendy.presentation.model

data class LoadDetail(
    val value: String = "",
) : FormData {
    override fun isCompleted() = value.isNotBlank()
}
