package com.uzun.pseudosendy.data.repository

import com.uzun.pseudosendy.data.remote.dto.DrivingRoute
import com.uzun.pseudosendy.data.remote.dto.Geocode
import com.uzun.pseudosendy.presentation.model.LngLat

interface MapsRepository {
    suspend fun getLocationBy(text: String) : Geocode
    suspend fun getDrivingRoute(start: LngLat, goal: LngLat) : DrivingRoute
}