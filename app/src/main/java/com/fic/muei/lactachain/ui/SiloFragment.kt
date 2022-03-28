package com.fic.muei.lactachain.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.fic.muei.lactachain.databinding.SiloTracesListBinding
import com.fic.muei.lactachain.databinding.TransportTracesListBinding
import com.fic.muei.lactachain.ui.component.SiloAdapter
import com.fic.muei.lactachain.ui.component.TransportAdapter
import com.fic.muei.lactachain.utils.ObserverSelection

class SiloFragment: Fragment() {
    private lateinit var binding: SiloTracesListBinding
    private val viewModel: LactachainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = SiloTracesListBinding.inflate(layoutInflater)

        val recycleView = binding.recyclerView
        val adapter = SiloAdapter({ silo ->
            viewModel.setSelectedSilo(silo)
            findNavController().navigate(TracesListDirections.actionTracesListToAddTransfer()) },
            listOf())
        recycleView.adapter = adapter
        viewModel.listItemSilo.observe(viewLifecycleOwner) { l ->
            if (l != null) {
                adapter.setData(l)
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