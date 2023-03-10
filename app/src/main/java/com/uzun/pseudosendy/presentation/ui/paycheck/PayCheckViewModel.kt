package com.uzun.pseudosendy.presentation.ui.paycheck

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naver.maps.geometry.LatLng
import com.uzun.pseudosendy.data.remote.dto.Route
import com.uzun.pseudosendy.data.repository.MapsRepository
import com.uzun.pseudosendy.presentation.model.LngLat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PayCheckViewModel @Inject constructor(
    private val mapsRepository: MapsRepository
) : ViewModel() {

    var route by mutableStateOf<Route?>(null)
        private set
    var pointList by mutableStateOf<List<LatLng>>(emptyList())
        private set

    fun getRoute(start: LngLat, goal : LngLat) {
        viewModelScope.launch {
            route = mapsRepository.getDrivingRoute(start, goal).route

            val newPointList = mutableListOf<LatLng>()
            Log.e("TEST", route!!.traoptimal.first().path.toString())
            route!!.traoptimal.first().path.forEach {
                newPointList.add(LatLng(it[1], it[0]))
            }
            Log.e("TEST", pointList.toString())
            pointList = newPointList
        }
    }

}