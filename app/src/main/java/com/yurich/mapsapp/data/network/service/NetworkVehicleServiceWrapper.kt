package com.yurich.mapsapp.data.network.service

import com.yurich.mapsapp.data.network.utils.toVehicles
import com.yurich.mapsapp.domain.Vehicle
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkVehicleServiceWrapper @Inject constructor(
    private val service: NetworkVehicleService
    ): VehicleDataSource {

    override suspend fun getVehicles(): List<Vehicle> =
        service.getVehicles().toVehicles()

}