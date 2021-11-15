package com.fic.muei.lactachain.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.fic.muei.lactachain.R
import com.fic.muei.lactachain.databinding.FragmentLogInBinding
import com.fic.muei.lactachain.databinding.FragmentSelectionBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [selection.newInstance] factory method to
 * create an instance of this fragment.
 */
class Selection : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentSelectionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectionBinding.inflate(layoutInflater)
        binding.farm.setOnClickListener{
                view->view.findNavController().navigate(SelectionDirections.actionSelectionToSearchFarm())
        }
        return binding.root
    }

}