package com.fic.muei.lactachain.ui

import com.fic.muei.lactachain.R
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.fic.muei.lactachain.databinding.FragmentAddMilkDeliveryBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class AddMilkDelivery : Fragment() {
    private lateinit var binding: FragmentAddMilkDeliveryBinding
    private val viewModel: LactachainViewModel by activityViewModels()

    private fun ShowMessage(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    binding = FragmentAddMilkDeliveryBinding.inflate(layoutInflater)

    viewModel.getReceptionSilosData()
    val spinner = binding.siloList
    spinner.adapter = ArrayAdapter(
        requireContext(),
        android.R.layout.simple_spinner_dropdown_item,
        listOf("---")
    )
    lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.receptionSiloState.collect { result ->
                when (result) {
                    is ListSiloItemUIState.Success ->{
                        val siloList: List<String> =
                            result.data.map { silo -> "${getString(R.string.receptionSilo)} ${silo.code}" }
                        val adapter = ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_spinner_dropdown_item,
                            siloList
                        )
                        spinner.adapter = adapter
                    }
                    is ListSiloItemUIState.Error -> {
                        Toast.makeText(
                            requireContext(),
                            result.exception.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }
        binding.addSiloButton.setOnClickListener { view ->
            view.findNavController()
                .navigate(AddMilkDeliveryDirections.actionAddMilkDeliveryToAddSilo())
        }

        val testWidget = binding.sample
        viewModel.milkCollectionInTransport.observe(viewLifecycleOwner) { milk ->
            if (milk != null) {
                binding.volume.setText(milk.volumn.toString())
            }
        }
        val tempWidget = binding.temperature
        binding.addMilkDelivery.setOnClickListener { view ->
            try {
                val test = testWidget.isActivated
                val temperature = tempWidget.text.toString().toInt()
                val silo = spinner.getSelectedItem().toString().split(" ")[1].toInt()
                viewModel.addMilkDelivery(test,temperature,silo)
            } catch (e: Exception) {
                ShowMessage("Volume is not a number.")
            }

        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.milkDeliveryState.collect { result ->
                    when (result) {
                        is MilkDeliveryUIState.Success -> {
                            val snack = Snackbar.make(
                                binding.root, "Milk deliveries added successfully.",
                                Snackbar.LENGTH_INDEFINITE
                            )
                            snack.setAction("ACCEPT", View.OnClickListener {
                                findNavController().navigate(AddMilkDeliveryDirections.actionAddMilkDeliveryToTracesList())
                            })
                            snack.show()

                        }
                        is MilkDeliveryUIState.Error -> {
                            ShowMessage(result.exception.message.toString())
                        }
                    }
                }
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }


}