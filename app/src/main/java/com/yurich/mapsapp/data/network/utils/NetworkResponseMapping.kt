package com.yurich.mapsapp.data.network.utils

import com.yurich.mapsapp.data.network.models.NetworkVehicleResponse
import com.yurich.mapsapp.domain.GeoCoordinates
import com.yurich.mapsapp.domain.Vehicle

fun NetworkVehicleResponse.toVehicles() =
    this.vehicles.map {
        Vehicle(
            address = it.address,
            coordinates = GeoCoordinates(
                latitude = it.coordinates[1],
                longitude = it.coordinates[0]
            ),
            engineType = it.engineType,
            exterior = it.exterior,
            fuel = it.fuel,
            interior = it.interior,
            name = it.name,
            vin = it.vin
        )
    }