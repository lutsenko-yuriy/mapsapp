package com.yurich.mapsapp.presentation.main.list

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yurich.mapsapp.R
import com.yurich.mapsapp.domain.Vehicle

class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var vehicle: Vehicle

    private val name: TextView = itemView.findViewById(R.id.vehicle_name)
    private val address: TextView = itemView.findViewById(R.id.vehicle_address)
    private val vin: TextView = itemView.findViewById(R.id.vehicle_vin)

    fun bind(vehicle: Vehicle) {
        this.vehicle = vehicle

        this.name.text = vehicle.name
        this.address.text = vehicle.address
        this.vin.text = vehicle.vin
    }

}