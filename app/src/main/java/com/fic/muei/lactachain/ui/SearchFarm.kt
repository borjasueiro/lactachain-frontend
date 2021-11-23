package com.fic.muei.lactachain.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    private val viewModel: LactachainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchFarmBinding.inflate(layoutInflater)
        binding.button.setOnClickListener{
            val farmId = binding.farmidfilter.text.toString()
            if (farmId.isNotEmpty()){
                try {
                    val farmIdAux = farmId.trim().toInt()
                    viewModel.getFarmData(farmIdAux)
                }catch (e: Exception){
                    Toast.makeText(context,"Not exists farmId", Toast.LENGTH_SHORT).show()
                }
            }
        }
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collectLatest{uiState ->
                    when (uiState) {
                        is FarmUIState.Success -> {
                            val action = SearchFarmDirections.actionSearchFarmToMilkCollection(uiState.farm)
                            view?.findNavController()?.navigate(action)
                        }
                        is FarmUIState.Error -> Toast.makeText(context,"Not exists farmId", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
            return binding.root
    }
}