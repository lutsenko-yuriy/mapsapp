package com.yurich.mapsapp.data.network.service

import com.yurich.mapsapp.models.Vehicle

interface VehicleDataSource {
    suspend fun getVehicles(): List<Vehicle>
}