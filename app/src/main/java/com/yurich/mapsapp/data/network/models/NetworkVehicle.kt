package com.yurich.mapsapp.data.network.models

import com.google.gson.annotations.SerializedName

data class NetworkVehicle(
    @SerializedName("address")
    val address: String,
    @SerializedName("coordinates")
    val coordinates: List<Double>,
    @SerializedName("engineType")
    val engineType: String,
    @SerializedName("exterior")
    val exterior: String,
    @SerializedName("fuel")
    val fuel: Int,
    @SerializedName("interior")
    val interior: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("vin")
    val vin: String
)