package com.fic.muei.lactachain.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fic.muei.lactachain.R
import com.fic.muei.lactachain.databinding.FragmentTraceDataBinding
import com.fic.muei.lactachain.databinding.FragmentTracesListBinding
import com.fic.muei.lactachain.databinding.SiloTracesListBinding
import com.fic.muei.lactachain.ui.component.FarmAdapter
import com.fic.muei.lactachain.ui.component.SiloTraceAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TraceData.newInstance] factory method to
 * create an instance of this fragment.
 */
class TraceData : Fragment() {
    private lateinit var binding: FragmentTraceDataBinding
    private val viewModel: LactachainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTraceDataBinding.inflate(layoutInflater)
        viewModel.traceData.observe(viewLifecycleOwner) { traceData ->
            if (traceData != null) {
                binding.TraceId.setText("Trace: ${traceData.id}")
                val farmAdapter = FarmAdapter(traceData.listFarms)
                binding.pager.adapter = farmAdapter
                binding.pager.layoutManager = LinearLayoutManager(context)
                binding.pager1.adapter = SiloTraceAdapter(traceData.listTransvase)
                binding.pager1.layoutManager = LinearLayoutManager(context)

            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/*        val tabLayout = binding.tabLayout
        val viewPager = binding.pager
        viewPager.adapter = ScreenSlidePagerAdapter(requireActivity())
        TabLayoutMediator(tabLayout, viewPager) {tab,position ->
            tab.text = tabItemList.get(position)
        }.attach()*/

    }
    // An equivalent ViewPager2 adapter class
    //inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
/*        override fun getItemCount(): Int = tabItemList.size
        override fun createFragment(position: Int): Fragment{
            return when(position){
                0 -> TransportFragment.newInstance()
                1 -> SiloFragment.newInstance()
                else -> TransportFragment.newInstance()
            }
        }*/
}