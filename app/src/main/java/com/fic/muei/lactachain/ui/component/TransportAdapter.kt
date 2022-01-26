package com.fic.muei.lactachain.ui.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.fic.muei.lactachain.R
import com.fic.muei.lactachain.model.MilkCollectionDataItem



class TransportAdapter(private var dataSet: List<MilkCollectionDataItem>): RecyclerView.Adapter<TransportAdapter.TransportViewHolder>() {
    var tracker: SelectionTracker<MilkCollectionDataItem>? = null
    inner class TransportViewHolder(itemView: View) : ViewHolder(itemView){
        val collectId:TextView
        val transporterName:TextView
        val date:TextView

        init {
            collectId = itemView.findViewById(R.id.collectId)
            transporterName = itemView.findViewById(R.id.transporter_name)
            date = itemView.findViewById(R.id.date)
        }
        fun bind(collectId:String, transportName:String, date:String,isActivated:Boolean=false){
            this.collectId.text = collectId
            this.transporterName.text = transportName
            this.date.text = date
            itemView.isActivated = isActivated
        }
        fun getData(): ItemDetailsLookup.ItemDetails<MilkCollectionDataItem> =
            object : ItemDetailsLookup.ItemDetails<MilkCollectionDataItem>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): MilkCollectionDataItem? = dataSet[position]
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransportViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transport, parent, false)

        return TransportViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransportViewHolder, position: Int) {
        val tmp = dataSet.get(position)
        tracker?.let {
            holder.bind(tmp.code.toString(),
                tmp.transporterName,
                tmp.date.subSequence(0,10).toString(),
                it.isSelected(tmp))
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setData(dataSet: List<MilkCollectionDataItem>){
        this.dataSet = dataSet
        notifyDataSetChanged()
    }
    fun getItem(position: Int) = dataSet[position]
    fun getPosition(milk: MilkCollectionDataItem?) = dataSet.indexOfFirst { it.code == milk?.code }

}