package com.yurich.mapsapp.presentation.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.yurich.mapsapp.R
import com.yurich.mapsapp.domain.Vehicle

class VehicleListAdapter(
    private val listener: OnVehicleItemClickListener
    ) : RecyclerView.Adapter<VehicleViewHolder>() {

    private val vehicles = mutableListOf<Vehicle>()

    override fun getItemCount(): Int = vehicles.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_single_vehicle, parent, false)
        val vehicleViewHolder = VehicleViewHolder(view)

        vehicleViewHolder.itemView.setOnClickListener {
            if (vehicleViewHolder.adapterPosition == NO_POSITION) {
                return@setOnClickListener
            }
            listener.onVehicleItemClicked(vehicleViewHolder.vehicle)
        }
        return vehicleViewHolder
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

    interface OnVehicleItemClickListener {
        fun onVehicleItemClicked(vehicle: Vehicle)
    }

}