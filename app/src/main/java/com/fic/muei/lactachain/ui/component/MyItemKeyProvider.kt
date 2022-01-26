package com.fic.muei.lactachain.ui.component

import androidx.recyclerview.selection.ItemKeyProvider
import com.fic.muei.lactachain.model.MilkCollectionDataItem

class MyItemKeyProvider(private val adapter: TransportAdapter) :
    ItemKeyProvider<MilkCollectionDataItem>(SCOPE_CACHED) {
    override fun getKey(position: Int): MilkCollectionDataItem = adapter.getItem(position)

    override fun getPosition(key: MilkCollectionDataItem): Int = adapter.getPosition(key)
}