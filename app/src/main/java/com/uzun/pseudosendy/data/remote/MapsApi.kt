package com.uzun.pseudosendy.data.remote

import com.uzun.pseudosendy.data.remote.dto.DrivingRoute
import com.uzun.pseudosendy.data.remote.dto.Geocode
import retrofit2.http.GET
import retrofit2.http.Query

interface MapsApi {

    @GET("/map-geocode/v2/geocode")
    suspend fun getLocationBy(
        @Query("query") text: String,
        @Query("count") count: Int,
    ): Geocode

    @GET("/map-direction/v1/driving")
    suspend fun getDrivingRoute(
        @Query("start") start: String,
        @Query("goal") goal: String,
    ): DrivingRoute
}