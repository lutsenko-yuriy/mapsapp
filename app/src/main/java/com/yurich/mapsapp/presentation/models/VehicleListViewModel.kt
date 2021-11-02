package com.yurich.mapsapp.presentation.models

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
class VehicleListViewModel @Inject constructor(private val source: VehicleDataSource) : ViewModel() {

    private val privateAvailableVehiclesViewState = MutableLiveData(listOf<Vehicle>())
    val availableVehiclesViewState: LiveData<List<Vehicle>> = privateAvailableVehiclesViewState

    init {
        viewModelScope.launch {
            privateAvailableVehiclesViewState.value?.run {
                privateAvailableVehiclesViewState.postValue(
                    source.getVehicles()
                )
            }
        }
    }

}