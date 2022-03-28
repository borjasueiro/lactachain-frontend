package com.fic.muei.lactachain.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fic.muei.lactachain.databinding.FragmentTracesListBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TracesList : Fragment() {
    private lateinit var binding:  FragmentTracesListBinding
    private val viewModel: LactachainViewModel by activityViewModels()
    private val tabItemList = listOf("TRANSPORT", "SILO")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getTracesLists()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTracesListBinding.inflate(layoutInflater)
        val progressBar = binding.progressBar
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.listItemUIState.collect { result ->
                    when (result){
                        is ListItemUIState.Success -> {
                            progressBar.visibility = View.GONE
                        }
                        is ListItemUIState.Loading -> {
                            progressBar.visibility = View.VISIBLE
                        }
                        is ListItemUIState.Error -> {
                            Toast.makeText(context,result.exception.message,Toast.LENGTH_LONG).show()
                            progressBar.visibility = View.GONE}
                    }
                }
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayout = binding.tabLayout
        val viewPager = binding.pager
        viewPager.adapter = ScreenSlidePagerAdapter(requireActivity())
        TabLayoutMediator(tabLayout, viewPager) {tab,position ->
            tab.text = tabItemList.get(position)
        }.attach()

    }
    // An equivalent ViewPager2 adapter class
    inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = tabItemList.size
        override fun createFragment(position: Int): Fragment{
            return when(position){
                0 -> TransportFragment.newInstance()
                1 -> SiloFragment.newInstance()
                else -> TransportFragment.newInstance()
            }
        }
    }

}

