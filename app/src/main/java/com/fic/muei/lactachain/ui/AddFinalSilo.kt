package com.fic.muei.lactachain.ui

import android.os.Bundle
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
import com.fic.muei.lactachain.databinding.FragmentAddSiloBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class AddFinalSilo : Fragment() {
    private lateinit var binding: FragmentAddSiloBinding
    private val viewModel: LactachainViewModel by activityViewModels()
    private var checkedSiloType : Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddSiloBinding.inflate(layoutInflater)
        return binding.root
    }
    private fun ShowMessage(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val siloTypeWidget = binding.siloType
        siloTypeWidget.setOnCheckedChangeListener { _, id ->
                // Check which radio button was clicked
                when (id) {
                    R.id.radio_storage -> {
                        checkedSiloType = 0
                    }
                    R.id.radio_final ->{
                            checkedSiloType = 1
                    }
            }
        }
        // Inflate the layout for this fragment
        binding.addSiloButton.setOnClickListener {
            //1 is Storage
            //2 is Final
            val siloType = if (checkedSiloType == 0) "1" else "2"
                viewModel.addFinalSilo(siloType)
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.finalSiloState.collect {result ->
                    when (result) {
                        is FinalSiloItemUIState.Success -> {
                            val snack = Snackbar.make(binding.root,"Silo added successfully.",
                                Snackbar.LENGTH_INDEFINITE)
                            snack.setAction("ACCEPT", View.OnClickListener {
                                //findNavController().navigate(AddSiloDirections.actionAddSiloToAddTransfer())
                            })
                            snack.show()
                        }
                        is FinalSiloItemUIState.Error -> ShowMessage(result.exception.message.toString())
                        is FinalSiloItemUIState -> {
                        }
                    }
                }
            }
        }
    }
}