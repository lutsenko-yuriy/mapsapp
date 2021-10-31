package com.yurich.mapsapp.ui.main.list

import androidx.recyclerview.widget.DiffUtil
import com.yurich.mapsapp.models.Vehicle

class VehicleDifferenceCallback(
    private val oldList: List<Vehicle>,
    private val newList: List<Vehicle>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].vin == newList[newItemPosition].vin

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

}
