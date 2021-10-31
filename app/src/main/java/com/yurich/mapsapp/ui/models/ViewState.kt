package com.yurich.mapsapp.ui.models

import com.yurich.mapsapp.models.Vehicle

data class ViewState(
    val selectedVehicle: Vehicle? = null,
    val availableVehicles: List<Vehicle> = listOf()
)