package com.uzun.pseudosendy.presentation.model

import com.naver.maps.geometry.LatLng

data class LngLat(
    val lng: Double = 0.0,
    val lat: Double = 0.0
) : FormData {
    fun formatIt(): String = (this.lng.toString() + "," + this.lat.toString())
    override fun isCompleted(): Boolean = lng != 0.0 && lat != 0.0
    fun toNaverLatLng() : LatLng = LatLng(lat, lng)
}
