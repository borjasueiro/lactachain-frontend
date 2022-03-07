package com.fic.muei.lactachain.ui.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.fic.muei.lactachain.R
import com.fic.muei.lactachain.model.MilkCollectionDataItem



class TransportAdapter(private val onItemClick: (MilkCollectionDataItem)->Unit, private var dataSet: List<MilkCollectionDataItem>): RecyclerView.Adapter<TransportAdapter.TransportViewHolder>() {
    inner class TransportViewHolder(itemView: View) : ViewHolder(itemView), View.OnClickListener{
        val collectId:TextView
        val volumn: TextView
        val transporterName:TextView
        val date:TextView

        init {
            collectId = itemView.findViewById(R.id.collectId)
            volumn = itemView.findViewById(R.id.volumn)
            transporterName = itemView.findViewById(R.id.transporter_name)
            date = itemView.findViewById(R.id.date)
            itemView.setOnClickListener(this)
        }
        fun bind(collectId:String, volumn:String, transportName:String, date:String,isActivated:Boolean=false){
            this.collectId.text = collectId
            this.volumn.text = volumn
            this.transporterName.text = transportName
            this.date.text = date
            itemView.isActivated = isActivated
        }

        override fun onClick(p0: View?) {
            onItemClick(dataSet[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransportViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transport, parent, false)

        return TransportViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransportViewHolder, position: Int) {
        val tmp = dataSet.get(position)
        holder.bind(tmp.code.toString(),
            tmp.volumn.toString(),
            tmp.transporterName,
            tmp.date.subSequence(0,10).toString())
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