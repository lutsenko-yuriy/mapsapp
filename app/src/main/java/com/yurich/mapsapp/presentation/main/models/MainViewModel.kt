package com.yurich.mapsapp.presentation.main.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yurich.mapsapp.data.network.service.VehicleDataSource
import com.yurich.mapsapp.domain.Vehicle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val source: VehicleDataSource) : ViewModel() {

    private val privateAvailableVehiclesViewState = MutableLiveData(listOf<Vehicle>())
    val availableVehiclesViewState: LiveData<List<Vehicle>> = privateAvailableVehiclesViewState

    private val privateSelectedVehicleViewState = MutableLiveData<Vehicle?>()
    val selectedVehicleViewState: LiveData<Vehicle?> = privateSelectedVehicleViewState

    init {
        viewModelScope.launch {
            privateAvailableVehiclesViewState.value?.run {
                privateAvailableVehiclesViewState.postValue(
                    source.getVehicles()
                )
            }
        }
    }

    fun selectVehicle(vehicle: Vehicle) {
        privateAvailableVehiclesViewState.value?.run {
            privateSelectedVehicleViewState.value = vehicle
        }
    }

    fun unselectVehicle() {
        privateSelectedVehicleViewState.value?.run {
            privateSelectedVehicleViewState.value = null
        }
    }
}