package com.fic.muei.lactachain.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.fic.muei.lactachain.R
import com.fic.muei.lactachain.databinding.FragmentLogInBinding
import com.fic.muei.lactachain.databinding.FragmentSearchFarmBinding
import com.fic.muei.lactachain.databinding.FragmentSelectionBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFarm.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFarm : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentSearchFarmBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchFarmBinding.inflate(layoutInflater)
        binding.button.setOnClickListener{
                view->view.findNavController().navigate(LogInDirections.actionLogInToSelection())
        }
        return binding.root
    }
}