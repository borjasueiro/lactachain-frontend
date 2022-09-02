package com.fic.muei.lactachain.ui

import android.app.AlertDialog
import android.os.Bundle
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
import com.fic.muei.lactachain.R
import com.fic.muei.lactachain.databinding.FragmentAddTransferBinding
import com.fic.muei.lactachain.ui.component.SiloDialogFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class AddTransfer : Fragment() {
    private lateinit var binding: FragmentAddTransferBinding
    private val viewModel: LactachainViewModel by activityViewModels()
    private lateinit var adapter: ArrayAdapter<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getFinalSilosData()
    binding = FragmentAddTransferBinding.inflate(layoutInflater)
        val spinner = binding.SiloDest
        spinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            listOf("---")
        )
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.finalSiloSpinnerState.collect { result ->
                    when (result) {
                        is ListFinalSiloItemUIState.Success -> {
                            val siloList: List<String> =
                                result.data.map { silo ->
                                    if (silo.type == "1") "Storage ${silo.code}" else "Final ${silo.code}"
                                }
                            adapter = ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_spinner_dropdown_item,
                                siloList
                            )
                            spinner.adapter = adapter as ArrayAdapter<*>

                        }
                        is ListFinalSiloItemUIState.Error -> {
                            Toast.makeText(
                                requireContext(),
                                result.exception.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        else -> throw RuntimeException()
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.finalSiloState.collect{ result ->
                    when (result){
                        is FinalSiloItemUIState.Success ->{
                            val snack = Snackbar.make(binding.root,
                                String.format(resources.getString(R.string.added_final_silo),result.code),
                                Snackbar.LENGTH_LONG)
                            snack.show()
                        }
                        is FinalSiloItemUIState.Error -> {
                            Toast.makeText(
                                requireContext(),
                                result.exception.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        is FinalSiloItemUIState -> {

                        }
                    }
                }
            }
        }
    binding.addTransferButton.setOnClickListener { _ ->
        SiloDialogFragment().show(childFragmentManager,SiloDialogFragment.TAG)
    }
        // Inflate the layout for this fragment
        return binding.root
    }


}