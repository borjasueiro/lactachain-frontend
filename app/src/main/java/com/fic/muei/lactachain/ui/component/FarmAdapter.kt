package com.fic.muei.lactachain.ui.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.fic.muei.lactachain.R
import com.fic.muei.lactachain.model.FarmData
import com.fic.muei.lactachain.model.FarmParcialData
import com.fic.muei.lactachain.model.MilkCollectionDataItem
import com.fic.muei.lactachain.model.SiloDataItem
import com.fic.muei.lactachain.network.model.FarmChainDto


class FarmAdapter(private val dataSet: List<FarmParcialData>) :
    RecyclerView.Adapter<FarmAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val farm: TextView
        val location: TextView
        val carPlate: TextView

        init {
            // Define click listener for the ViewHolder's View.
            farm = view.findViewById(R.id.farmName)
            location = view.findViewById(R.id.location)
            carPlate = view.findViewById(R.id.carPlate)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_farms, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val farm = dataSet[position]
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.farm.text = farm.name
        viewHolder.location.text = farm.town
        viewHolder.carPlate.text = farm.transportId
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
