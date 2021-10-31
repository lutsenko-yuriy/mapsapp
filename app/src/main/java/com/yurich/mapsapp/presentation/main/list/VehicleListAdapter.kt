package com.yurich.mapsapp.presentation.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yurich.mapsapp.R
import com.yurich.mapsapp.domain.Vehicle

class VehicleListAdapter : RecyclerView.Adapter<VehicleViewHolder>() {

    private val vehicles = mutableListOf<Vehicle>()

    override fun getItemCount(): Int = vehicles.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_single_vehicle, parent, false)
        return VehicleViewHolder(view)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.bind(vehicles[position])
    }

    fun updateList(newVehicles: List<Vehicle>) {
        val difference = DiffUtil.calculateDiff(VehicleDifferenceCallback(vehicles, newVehicles))

        vehicles.clear()
        vehicles.addAll(newVehicles)

        difference.dispatchUpdatesTo(this)
    }
}