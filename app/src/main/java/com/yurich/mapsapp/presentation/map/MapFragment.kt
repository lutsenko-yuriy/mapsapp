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
import androidx.fragment.app.activityViewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.yurich.mapsapp.R
import com.yurich.mapsapp.domain.Vehicle
import com.yurich.mapsapp.presentation.models.VehicleListViewModel
import com.yurich.mapsapp.presentation.models.VehicleSelectionViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MapFragment : Fragment() {

    companion object {
        fun newInstance() = MapFragment()
    }

    private val vehicleListViewModel: VehicleListViewModel by activityViewModels()
    private val vehicleSelectionViewModel: VehicleSelectionViewModel by activityViewModels()

    private lateinit var mapView: MapView
    private lateinit var map: GoogleMap

    private val markers = mutableMapOf<Vehicle, Marker?>()
    private var selectedVehicle: Vehicle? = null

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

        initializeMap(view, savedInstanceState)
    }

    private fun initializeMap(view: View, savedInstanceState: Bundle?) {
        mapView = view.findViewById(R.id.map_view)
        mapView.onCreate(savedInstanceState)

        mapView.getMapAsync { map ->
            this@MapFragment.map = map

            map.setOnMarkerClickListener { marker ->
                markers.filterValues { it == marker }
                    .firstNotNullOfOrNull { it.key }
                    ?.let {
                        if (selectedVehicle != null) {
                            vehicleSelectionViewModel.unselectVehicle()
                        } else {
                            vehicleSelectionViewModel.selectVehicle(it)
                        }
                    }
                true
            }

            map.setOnMapClickListener {
                vehicleSelectionViewModel.unselectVehicle()
            }

            showUserLocationOrRequestPermission()
            vehicleListViewModel.availableVehiclesViewState.observe(viewLifecycleOwner) {
                updateAvailableVehiclesOnMap(it)
            }

            vehicleSelectionViewModel.selectedVehicleViewState.observe(viewLifecycleOwner) {
                updateSelectedVehicleOnMap(it)
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

    private fun updateAvailableVehiclesOnMap(availableVehicles: List<Vehicle>) {
        map.clear()
        markers.clear()

        val bounds = LatLngBounds.builder()

        for (vehicle in availableVehicles) {
            val latLng = LatLng(vehicle.coordinates.latitude, vehicle.coordinates.longitude)

            if (markers[vehicle] == null) {
                markers[vehicle] =
                    map.addMarker(
                        MarkerOptions()
                            .position(latLng)
                            .title(vehicle.name)
                    )
            }

            bounds.include(latLng)
        }

        map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 100))
    }

    private fun updateSelectedVehicleOnMap(selectedVehicle: Vehicle?) {
        this.selectedVehicle = selectedVehicle
        for (entry in markers) {
            val selected = entry.key == selectedVehicle
            if (selected) {
                entry.value?.showInfoWindow()
            }

            val visible = selected || selectedVehicle == null
            entry.value?.isVisible = visible
        }

        selectedVehicle?.run {
            map.moveCamera(
                CameraUpdateFactory.newLatLng(
                    LatLng(coordinates.latitude, coordinates.longitude)
                )
            )
        }
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