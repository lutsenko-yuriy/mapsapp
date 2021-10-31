package com.yurich.mapsapp.ui.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yurich.mapsapp.data.network.service.VehicleDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val source: VehicleDataSource
): ViewModel() {

    private val _privateViewState = MutableLiveData(ViewState())
    val viewState: LiveData<ViewState> = _privateViewState

    init {

    }
}