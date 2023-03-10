package com.uzun.pseudosendy.presentation.ui.orderform.location

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uzun.pseudosendy.data.repository.MapsRepository
import com.uzun.pseudosendy.presentation.model.LngLat
import com.uzun.pseudosendy.presentation.model.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val mapsRepository: MapsRepository,
) : ViewModel() {

    var addrList by mutableStateOf(LocationList())
        private set

    fun getSearchedLocationList(query: String) = viewModelScope.launch {
        val res = mapsRepository.getLocationBy(query)
        val list = res.addresses.map { addr ->
            Location(
                addr.jibunAddress,
                addr.roadAddress,
                LngLat(addr.x.toDouble(), addr.y.toDouble())
            )
        }
        if (res.status != "OK") return@launch
        addrList = LocationList(list = list)
    }

    fun resetAddrList() {
        addrList = LocationList()
    }
}

data class LocationList(val list: List<Location> = emptyList())
