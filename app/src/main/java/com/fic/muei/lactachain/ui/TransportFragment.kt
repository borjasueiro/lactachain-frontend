package com.fic.muei.lactachain.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.fic.muei.lactachain.databinding.TransportTracesListBinding
import com.fic.muei.lactachain.model.MilkCollectionDataItem
import com.fic.muei.lactachain.ui.component.TransportAdapter

class TransportFragment: Fragment() {
    private lateinit var binding: TransportTracesListBinding
    private val viewModel: LactachainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = TransportTracesListBinding.inflate(layoutInflater)

        val recycleView = binding.recyclerView
        recycleView.adapter = TransportAdapter(listOf())
        viewModel.listItemTransport.observe(viewLifecycleOwner) { l ->
            if (l != null) {
                (recycleView.adapter as TransportAdapter).setData(l)
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