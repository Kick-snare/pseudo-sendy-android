package com.uzun.pseudosendy.presentation.model

data class VehicleOptions(
    val vehicleType: String = "",
    val vehicleOption: String = ""
) : FormData {
    override fun isCompleted() = vehicleOption.isNotBlank() && vehicleType.isNotBlank()
}
