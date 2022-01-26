package com.fic.muei.lactachain.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.fic.muei.lactachain.databinding.FragmentSelectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Selection : Fragment() {
    private lateinit var binding: FragmentSelectionBinding
    private val viewModel: LactachainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectionBinding.inflate(layoutInflater)
        val nextButton = binding.next
        val args = SelectionArgs.fromBundle(requireArguments())
        val btnState = args.btnState
        nextButton.text = if (!btnState) "Add Collection" else "See Traces" //False is transporter
        nextButton.setOnClickListener{view->
            if (!btnState)
                view.findNavController().navigate(SelectionDirections.actionSelectionToSearchFarm())
            else
                view.findNavController().navigate(SelectionDirections.actionSelectionToTracesList())
        }
        return binding.root
    }

}