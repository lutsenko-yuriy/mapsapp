package com.yurich.mapsapp.data.network.service

import com.yurich.mapsapp.data.network.models.NetworkVehicleResponse
import retrofit2.http.GET

interface NetworkVehicleService {

    @GET("locations.json")
    suspend fun getVehicles(): NetworkVehicleResponse

}