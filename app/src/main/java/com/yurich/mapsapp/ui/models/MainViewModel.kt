package com.yurich.mapsapp.ui.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yurich.mapsapp.data.network.service.VehicleDataSource
import com.yurich.mapsapp.models.Vehicle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val source: VehicleDataSource): ViewModel() {

    private val _privateViewState = MutableLiveData(ViewState())
    val viewState: LiveData<ViewState> = _privateViewState

    init {
        viewModelScope.launch {
            _privateViewState.value?.run {
                _privateViewState.postValue(
                    this.copy(availableVehicles = source.getVehicles())
                )
            }
        }
    }

    fun selectVehicle(vehicle: Vehicle) {
        _privateViewState.value?.run {
            _privateViewState.value = this.copy(selectedVehicle = vehicle)
        }
    }

    fun unselectVehicle() {
        _privateViewState.value?.run {
            _privateViewState.value = this.copy(selectedVehicle = null)
        }
    }
}