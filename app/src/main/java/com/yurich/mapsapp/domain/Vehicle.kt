package com.yurich.mapsapp.domain

data class Vehicle(
    val address: String,
    val coordinates: GeoCoordinates,
    val engineType: String,
    val exterior: String,
    val fuel: Int,
    val interior: String,
    val name: String,
    val vin: String
)
