package com.fic.muei.lactachain.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.fic.muei.lactachain.databinding.FragmentAddTransferBinding
import com.fic.muei.lactachain.databinding.TransportTracesListBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class AddTransfer : Fragment() {
    private lateinit var binding: FragmentAddTransferBinding
    private val viewModel: LactachainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    binding = FragmentAddTransferBinding.inflate(layoutInflater)

    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.transportStateCreated.collect { result ->
                when (result) {
                }
            }
        }
    }
        // Inflate the layout for this fragment
        return binding.root
    }


}