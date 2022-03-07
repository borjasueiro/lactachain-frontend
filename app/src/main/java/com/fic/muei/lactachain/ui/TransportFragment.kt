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
import com.fic.muei.lactachain.databinding.TransportTracesListBinding
import com.fic.muei.lactachain.ui.component.TransportAdapter
import com.fic.muei.lactachain.utils.ObserverSelection

class TransportFragment: Fragment() {
    private lateinit var binding: TransportTracesListBinding
    private val viewModel: LactachainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = TransportTracesListBinding.inflate(layoutInflater)

        val recycleView = binding.recyclerView
        val adapter = TransportAdapter({milk ->
            viewModel.setSelectedMilkColletion(milk)
            findNavController().navigate(TracesListDirections.actionAddTracesListToMilkDelivery()) },
            listOf())
        recycleView.adapter = adapter
        viewModel.listItemTransport.observe(viewLifecycleOwner) { l ->
            if (l != null) {
                adapter.setData(l)
            }
        }
        return binding.root
    }
    companion object{
        @JvmStatic
        fun newInstance() =
            TransportFragment()
    }

}