package com.uzun.pseudosendy.presentation.model

data class ServiceOptions(
    val serviceOption : String = "",
    val rideWith: Boolean = false
) : FormData {
    override fun isCompleted() = serviceOption.isNotBlank()
}