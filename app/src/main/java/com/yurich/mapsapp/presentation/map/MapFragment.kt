package com.yurich.mapsapp.presentation.map

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.yurich.mapsapp.R
import com.yurich.mapsapp.domain.Vehicle
import com.yurich.mapsapp.presentation.main.models.MainViewModel
import com.yurich.mapsapp.presentation.main.models.ViewState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MapFragment : Fragment() {

    companion object {
        fun newInstance() = MapFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var mapView: MapView
    private lateinit var map: GoogleMap

    @SuppressLint("MissingPermission")
    private val locationPermissionRequest: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            val mapsEnabled = it[Manifest.permission.ACCESS_COARSE_LOCATION] == true ||
                    it[Manifest.permission.ACCESS_FINE_LOCATION] == true

            if (mapsEnabled) {
                map.isMyLocationEnabled = true
                setMapUi()
            }
        }

    private fun setMapUi() {
        map.uiSettings.isMyLocationButtonEnabled = true
        map.uiSettings.isZoomControlsEnabled = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.map_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this.requireActivity())[MainViewModel::class.java]

        initializeMap(view, savedInstanceState)
    }

    private fun initializeMap(view: View, savedInstanceState: Bundle?) {
        mapView = view.findViewById(R.id.map_view)
        mapView.onCreate(savedInstanceState)

        mapView.getMapAsync { map ->
            this@MapFragment.map = map

            showUserLocationOrRequestPermission()
            viewModel.viewState.observe(viewLifecycleOwner) {
                updateMapState(it)
            }
        }
    }

    private fun showUserLocationOrRequestPermission() {
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
    }

    private fun updateMapState(state: ViewState) {
        map.clear()
        if (state.selectedVehicle != null) {
            drawMarkerForVehicle(map, state.selectedVehicle)
        } else {
            for (vehicle in state.availableVehicles) {
                drawMarkerForVehicle(map, vehicle)
            }
        }
    }

    private fun drawMarkerForVehicle(map: GoogleMap, vehicle: Vehicle) {
        map.addMarker(
            MarkerOptions()
                .position(LatLng(vehicle.coordinates.latitude, vehicle.coordinates.longitude))
                .title(vehicle.name)
        )
    }

    override fun onResume() {
        mapView.onResume()
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

}