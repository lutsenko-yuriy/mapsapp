package com.yurich.mapsapp.presentation.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yurich.mapsapp.R
import com.yurich.mapsapp.presentation.main.list.VehicleListAdapter
import com.yurich.mapsapp.presentation.main.models.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var vehiclesList: RecyclerView
    private lateinit var vehiclesAdapter: VehicleListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeList(view)
        initializeViewModel()
    }

    private fun initializeList(view: View) {
        vehiclesList = view.findViewById(R.id.vehicle_list)
        vehiclesList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        vehiclesList.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        vehiclesAdapter = VehicleListAdapter()

        vehiclesList.adapter = vehiclesAdapter
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this.requireActivity())[MainViewModel::class.java]

        viewModel.viewState.observe(viewLifecycleOwner) {
            vehiclesAdapter.updateList(it.availableVehicles)
        }
    }


}