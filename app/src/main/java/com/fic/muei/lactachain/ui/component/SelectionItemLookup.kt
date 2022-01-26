package com.fic.muei.lactachain.ui.component

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.fic.muei.lactachain.model.MilkCollectionDataItem

class SelectionItemLookup(private val recyclerView: RecyclerView): ItemDetailsLookup<MilkCollectionDataItem>(){
    override fun getItemDetails(event: MotionEvent): ItemDetails<MilkCollectionDataItem>? {
        val view = recyclerView.findChildViewUnder(event.x, event.y)
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as TransportAdapter.TransportViewHolder)
                .getData()
        }
        return null
    }
}