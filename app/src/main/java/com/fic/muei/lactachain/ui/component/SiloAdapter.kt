package com.fic.muei.lactachain.ui.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.fic.muei.lactachain.R
import com.fic.muei.lactachain.model.MilkCollectionDataItem
import com.fic.muei.lactachain.model.SiloDataItem


class SiloAdapter(private val onItemClick: (SiloDataItem)->Unit, private var dataSet: List<SiloDataItem>): RecyclerView.Adapter<SiloAdapter.SiloViewHolder>() {
    inner class SiloViewHolder(itemView: View) : ViewHolder(itemView), View.OnClickListener{
        val code:TextView
        val type: TextView
        init {
            code = itemView.findViewById(R.id.code)
            type = itemView.findViewById(R.id.type)
            itemView.setOnClickListener(this)
        }
        fun bind(code:String, type:String){
            this.code.text = code
            when (type){
                "1" -> this.type.text = "Silo Storage"
                "2" -> this.type.text = "Silo Final"
                else -> this.type.text = "Silo Recept"
            }
        }

        override fun onClick(p0: View?) {
            onItemClick(dataSet[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiloAdapter.SiloViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_silo, parent, false)

        return SiloViewHolder(view)
    }

    override fun onBindViewHolder(holder: SiloAdapter.SiloViewHolder, position: Int) {
        val tmp = dataSet.get(position)
        holder.bind(tmp.code.toString(),
            tmp.type.toString()
        )
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setData(dataSet: List<SiloDataItem>){
        this.dataSet = dataSet
        notifyDataSetChanged()
    }
    fun getItem(position: Int) = dataSet[position]
    fun getPosition(milk: SiloDataItem?) = dataSet.indexOfFirst { it.code == milk?.code }

}