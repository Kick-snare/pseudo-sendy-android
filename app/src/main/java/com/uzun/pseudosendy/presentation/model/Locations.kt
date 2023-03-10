package com.uzun.pseudosendy.presentation.model

data class Locations(
    val depart: Location = Location(),
    val arrive: Location = Location(),
) : FormData {
    override fun isCompleted() = depart.isCompleted() && arrive.isCompleted()
}
