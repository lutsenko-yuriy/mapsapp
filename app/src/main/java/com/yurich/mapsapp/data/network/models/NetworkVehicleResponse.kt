package com.yurich.mapsapp.data.network.models

import com.google.gson.annotations.SerializedName

data class NetworkVehicleResponse(
    @SerializedName("placemarks")
    val vehicles: List<NetworkVehicle>
)