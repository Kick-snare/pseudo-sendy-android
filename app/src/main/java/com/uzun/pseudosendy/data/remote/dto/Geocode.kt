package com.uzun.pseudosendy.data.remote.dto

data class Geocode(
    val status: String,
    val errorMessage: String,
    val addresses: List<Address>,
)