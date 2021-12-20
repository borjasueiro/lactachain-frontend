package com.fic.muei.lactachain.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.fic.muei.lactachain.R
import com.fic.muei.lactachain.databinding.FragmentAddTransportBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [AddTransport.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class AddTransport : Fragment() {

    private lateinit var binding: FragmentAddTransportBinding
    private val viewModel: LactachainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTransportBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun ShowMessage(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val carPlateWidget = binding.carPlate
        val tankCodeWidget = binding.tankCode
        val capacityWidget = binding.capacity
        val currentWidget = binding.current
        // Inflate the layout for this fragment
        binding.addTransportButton.setOnClickListener {
            val carPlate = carPlateWidget.text.toString()
            val tankCode = tankCodeWidget.text.toString()
            val capacity = capacityWidget.text.toString()
            val current = currentWidget.isActivated
            if (carPlate.isEmpty()) {
                ShowMessage("Car Plate is empty.")
            } else if (tankCode.isEmpty()) {
                ShowMessage("Tank Code is empty.")
            } else if (capacity.isEmpty()) {
                ShowMessage("Capacity is empty.")
            }else if (capacity.toInt()<=0) {
                ShowMessage("Capacity is not a positive number.")
            }else {
                viewModel.addTransport(carPlate, tankCode, capacity.toInt(), current)
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.transportStateCreated.collect {result ->
                    when (result) {
                        is TransportUIStateCreated.Success -> findNavController().navigate(AddTransportDirections.actionAddTransportToMilkCollection())
                        is TransportUIStateCreated.Error -> ShowMessage(result.exception.message.toString())
                    }
                }
            }
        }
    }
}