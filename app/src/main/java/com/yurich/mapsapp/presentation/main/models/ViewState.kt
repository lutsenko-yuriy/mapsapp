package com.yurich.mapsapp.presentation.main.models

import com.yurich.mapsapp.domain.Vehicle

data class ViewState(
    val selectedVehicle: Vehicle? = null,
    val availableVehicles: List<Vehicle> = listOf()
)