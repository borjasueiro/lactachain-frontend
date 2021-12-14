package com.fic.muei.lactachain.ui

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
import com.fic.muei.lactachain.databinding.FragmentMilkCollectionBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [MilkCollection.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class MilkCollection : Fragment() {
    private lateinit var binding: FragmentMilkCollectionBinding
    private val viewModel: LactachainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMilkCollectionBinding.inflate(layoutInflater)
        val args = MilkCollectionArgs.fromBundle(requireArguments())
        binding.FarmName.text = args.farm.name
        binding.townHall.setText(args.farm.town)
        viewModel.transporter.observe(viewLifecycleOwner){transporter ->
            run {
                if (transporter != null) {
                    binding.transporter.setText(transporter.name)
                    viewModel.getTransportData()
                }
            }
        }
        val spinner = binding.carPlate
        spinner.adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item, listOf("---"))
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.transportState.collect { result ->
                    when (result) {
                        is TransportUIState.Success -> {
                            val carPlates:List<String> = result.data.map { car -> car.car_registration }
                            val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,carPlates)
                            spinner.adapter = adapter
                        }
                        is TransportUIState.Error -> {
                            Toast.makeText(requireContext(),result.exception.message,Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
        return binding.root
    }

}