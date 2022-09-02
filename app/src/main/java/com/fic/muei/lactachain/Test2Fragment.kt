package com.fic.muei.lactachain

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
import com.fic.muei.lactachain.databinding.FragmentTest2Binding
import com.fic.muei.lactachain.ui.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class Test2Fragment: Fragment() {
    private lateinit var binding: FragmentTest2Binding
    private val viewModel: LactachainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentTest2Binding.inflate(layoutInflater)

        val text = binding.test
        val button2 = binding.button2
        button2.setOnClickListener{_ -> viewModel.getAllFarmsChain()}
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.farmChainState.collectLatest{uiState ->
                    when (uiState) {
                        is FarmChainUIState.Success -> {
                            text.text = uiState.data.joinToString ( prefix="[", postfix ="]" )
                            { "{'id':${it.name}, 'location':${it.town}}" }
                        }
                        is FarmChainUIState.Error -> Toast.makeText(context,uiState.exception.message, Toast.LENGTH_SHORT).show()
                        is FarmChainUIState.Empty -> {}
                    }
                }
            }
        }
        return binding.root
    }
    companion object{
        @JvmStatic
        fun newInstance() =
            SiloFragment()
    }

}