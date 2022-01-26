package com.fic.muei.lactachain.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import com.fic.muei.lactachain.databinding.TransportTracesListBinding
import com.fic.muei.lactachain.model.MilkCollectionDataItem
import com.fic.muei.lactachain.ui.component.MyItemKeyProvider
import com.fic.muei.lactachain.ui.component.SelectionItemLookup
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
        val adapter = TransportAdapter(listOf())
        recycleView.adapter = adapter
        viewModel.listItemTransport.observe(viewLifecycleOwner) { l ->
            if (l != null) {
                adapter.setData(l)
            }
        }
        val tracker = SelectionTracker.Builder<MilkCollectionDataItem>(
            "mySelection",
            recycleView,
            MyItemKeyProvider(adapter),
            SelectionItemLookup(recycleView),
            StorageStrategy.createParcelableStorage(MilkCollectionDataItem::class.java)
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()
        adapter.tracker = tracker
        return binding.root
    }
    companion object{
        @JvmStatic
        fun newInstance() =
            TransportFragment()
    }

}