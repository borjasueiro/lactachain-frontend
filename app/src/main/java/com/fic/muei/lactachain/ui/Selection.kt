package com.fic.muei.lactachain.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.fic.muei.lactachain.databinding.FragmentSelectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Selection : Fragment() {
    private lateinit var binding: FragmentSelectionBinding
    private val viewModel: LactachainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectionBinding.inflate(layoutInflater)
        val farmButton = binding.farm
        val plantButton = binding.plant
        val args = SelectionArgs.fromBundle(requireArguments())
        val btnState = args.btnState
        farmButton.setEnabled(!btnState) //False is transporter
        plantButton.setEnabled(btnState) //True is operator
        farmButton.setOnClickListener{
                view->view.findNavController().navigate(SelectionDirections.actionSelectionToSearchFarm())
        }
        plantButton.setOnClickListener{
                view->view.findNavController().navigate(SelectionDirections.actionSelectionToSearchFarm())
        }
        return binding.root
    }

}