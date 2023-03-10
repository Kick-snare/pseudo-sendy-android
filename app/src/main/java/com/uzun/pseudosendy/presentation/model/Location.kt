package com.uzun.pseudosendy.presentation.model

data class Location (
    val jibunAddr: String = "",
    val roadAddr: String = "",
    val lngLat: LngLat = LngLat()
) : FormData {
    override fun isCompleted(): Boolean =
        jibunAddr.isNotBlank() && roadAddr.isNotBlank() && lngLat.isCompleted()
}
