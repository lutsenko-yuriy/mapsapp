package com.yurich.mapsapp.presentation.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.yurich.mapsapp.R
import com.yurich.mapsapp.domain.Vehicle
import com.yurich.mapsapp.presentation.main.models.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : Fragment() {

    companion object {
        fun newInstance() = MapFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var mapView: MapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.map_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this.requireActivity())[MainViewModel::class.java]

        initializeMap()
    }

    private fun initializeMap() {
        val view = this.view ?: return

        mapView = view.findViewById(R.id.map_view)
        mapView.getMapAsync { map ->
            viewModel.viewState.observe(viewLifecycleOwner) {
                map.clear()
                if (it.selectedVehicle != null) {
                    drawMarkerForVehicle(map, it.selectedVehicle)
                } else {
                    for (vehicle in it.availableVehicles) {
                        drawMarkerForVehicle(map, vehicle)
                    }
                }
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

}