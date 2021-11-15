package com.fic.muei.lactachain.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.fic.muei.lactachain.databinding.FragmentSearchFarmBinding
import dagger.hilt.android.AndroidEntryPoint
/**
 * A simple [Fragment] subclass.
 * Use the [SearchFarm.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SearchFarm : Fragment() {
    private lateinit var binding: FragmentSearchFarmBinding
    private val viewModel: LactachainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchFarmBinding.inflate(layoutInflater)
        binding.button.setOnClickListener{
            val farmid = binding.farmidfilter.text.toString()
            if (farmid.length>0){
                try {
                    val farmidAux = farmid.trim().toInt()
                    viewModel.getFarmData(farmidAux)
                }catch (e: Exception){
                    Toast.makeText(context,"Not exists farmid", Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.farm.observe(viewLifecycleOwner,{farm ->
            if(farm != null){
                binding.textView.text = farm.name
            }
        })
        return binding.root
    }
}