package com.fic.muei.lactachain.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.fic.muei.lactachain.databinding.FragmentSearchFarmBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFarm.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SearchFarm : Fragment() {
    private lateinit var binding: FragmentSearchFarmBinding
    private val viewModel: LactachainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchFarmBinding.inflate(layoutInflater)
        binding.button.setOnClickListener{
            val farmId = binding.farmidfilter.text.toString()
            if (farmId.isNotEmpty()){
                val farmIdAux = farmId.trim().toInt()
                viewModel.getFarmData(farmIdAux)
            }
        }
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.farmState.collectLatest{uiState ->
                    when (uiState) {
                        is FarmUIState.Success -> {
                            view?.findNavController()?.navigate(SearchFarmDirections.actionSearchFarmToMilkCollection())
                        }
                        is FarmUIState.Error -> Toast.makeText(context,uiState.exception.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
            return binding.root
    }
}