package com.uzun.pseudosendy.presentation.model

data class Locations(
    val depart: String = "",
    val arrive: String = ""
) : FormData {
    override fun isCompleted() = depart.isNotBlank() && arrive.isNotBlank()
}
