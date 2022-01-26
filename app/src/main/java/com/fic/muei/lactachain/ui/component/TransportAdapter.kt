package com.fic.muei.lactachain.ui.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.fic.muei.lactachain.R
import com.fic.muei.lactachain.model.MilkCollectionDataItem
import java.text.SimpleDateFormat



class TransportAdapter(private var dataSet: List<MilkCollectionDataItem>): RecyclerView.Adapter<TransportAdapter.TransportViewHolder>() {

    class TransportViewHolder(itemView: View) : ViewHolder(itemView){
        val collectId:TextView
        val transporterName:TextView
        val date:TextView

        init {
            collectId = itemView.findViewById(R.id.collectId)
            transporterName = itemView.findViewById(R.id.transporter_name)
            date = itemView.findViewById(R.id.date)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransportViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transport, parent, false)

        return TransportViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransportViewHolder, position: Int) {
        val tmp = dataSet.get(position)
        holder.collectId.text = tmp.code.toString()
        holder.transporterName.text = tmp.transporterName
        holder.date.text = tmp.date.subSequence(0,10)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setData(dataSet: List<MilkCollectionDataItem>){
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

}