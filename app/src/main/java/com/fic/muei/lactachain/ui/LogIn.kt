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
import com.fic.muei.lactachain.databinding.FragmentLogInBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [LogIn.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class LogIn : Fragment() {
    private lateinit var binding: FragmentLogInBinding
    private val viewModel: LactachainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(layoutInflater)
        val qrButton = binding.scanQrTrace
        val user = binding.User
        val password = binding.Password
        binding.logIn.setOnClickListener{
            run {
                val nif = user.text.toString()
                viewModel.setAuthData(nif,password.text.toString())
                viewModel.getTransporterData(nif)
            }
        }
        binding.scanQrTrace.setOnClickListener{
            view?.findNavController()?.navigate(LogInDirections.actionLogInToScanQR())
        }
        lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginState.collectLatest{uiState ->
                    when (uiState) {
                        is LoginUIState.Success -> {
                            view?.findNavController()?.navigate(LogInDirections.actionLogInToSelection(uiState.data))
                        }
                        is LoginUIState.Error -> Toast.makeText(context,uiState.exception.message, Toast.LENGTH_LONG).show()
                        is LoginUIState.Empty -> {}
                    }
                }
            }
        }
        return binding.root
    }
}