package com.fic.muei.lactachain.ui.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fic.muei.lactachain.R
import com.fic.muei.lactachain.model.TransvaseData


class SiloTraceAdapter(private val dataSet: List<TransvaseData>) :
    RecyclerView.Adapter<SiloTraceAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val srcSilo: TextView
        val temperature: TextView
        val date: TextView

        init {
            // Define click listener for the ViewHolder's View.
            srcSilo = view.findViewById(R.id.src_silo)
            temperature = view.findViewById(R.id.temperaturereSilo)
            date = view.findViewById(R.id.transvaseDate)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_transvase, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        var srcSilo = dataSet[position]
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.srcSilo.text = srcSilo.siloSrc
        viewHolder.temperature.text = srcSilo.temperature
        viewHolder.date.text = srcSilo.date
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}