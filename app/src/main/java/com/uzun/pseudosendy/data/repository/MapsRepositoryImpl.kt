package com.uzun.pseudosendy.data.repository

import com.uzun.pseudosendy.data.remote.MapsApi
import com.uzun.pseudosendy.data.remote.dto.DrivingRoute
import com.uzun.pseudosendy.data.remote.dto.Geocode
import com.uzun.pseudosendy.presentation.model.LngLat
import javax.inject.Inject

class MapsRepositoryImpl @Inject constructor(
    private val api: MapsApi
) : MapsRepository {
    override suspend fun getLocationBy(text: String): Geocode {
        return api.getLocationBy(text, 20)
    }

    override suspend fun getDrivingRoute(start: LngLat, goal: LngLat): DrivingRoute {
        return api.getDrivingRoute(start.formatIt(), goal.formatIt())
    }
}