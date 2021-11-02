package com.yurich.mapsapp.presentation.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yurich.mapsapp.domain.Vehicle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VehicleSelectionViewModel @Inject constructor() : ViewModel() {

    private val privateSelectedVehicleViewState = MutableLiveData<Vehicle?>()
    val selectedVehicleViewState: LiveData<Vehicle?> = privateSelectedVehicleViewState


    fun selectVehicle(vehicle: Vehicle) {
        privateSelectedVehicleViewState.postValue(vehicle)
    }

    fun unselectVehicle() {
        privateSelectedVehicleViewState.postValue(null)
    }
}