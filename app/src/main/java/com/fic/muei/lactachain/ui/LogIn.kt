package com.fic.muei.lactachain.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.fic.muei.lactachain.databinding.FragmentLogInBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [LogIn.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class LogIn : Fragment() {
    private lateinit var binding: FragmentLogInBinding
    private val viewModel: LactachainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(layoutInflater)
        val user = binding.User
        val password = binding.Password
        binding.logIn.setOnClickListener{
            view->run {
                viewModel.setAuthData(user.text.toString(),password.text.toString())
                view.findNavController().navigate(LogInDirections.actionLogInToSelection())
            }
        }
        return binding.root
    }
}