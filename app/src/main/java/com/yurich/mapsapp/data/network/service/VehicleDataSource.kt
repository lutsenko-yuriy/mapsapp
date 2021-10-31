package com.yurich.mapsapp.data.network.service

import com.yurich.mapsapp.domain.Vehicle

interface VehicleDataSource {
    suspend fun getVehicles(): List<Vehicle>
}