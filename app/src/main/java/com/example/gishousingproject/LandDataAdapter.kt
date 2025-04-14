package com.example.gishousingproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gishousingproject.R

class LandDataAdapter(
    private val onClick: (LandData) -> Unit
) : ListAdapter<LandData, LandDataAdapter.LandViewHolder>(DIFF_CALLBACK) {

    inner class LandViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textSoil: TextView = itemView.findViewById(R.id.textSoil)
        private val textElevation: TextView = itemView.findViewById(R.id.textElevation)
//        private val textSuitability: TextView = itemView.findViewById(R.id.textSuitability)
        private val textRisk: TextView = itemView.findViewById(R.id.textRisk)
//        private val textInfra: TextView = itemView.findViewById(R.id.textInfra)
        private val textDensity: TextView = itemView.findViewById(R.id.textDensity)

        fun bind(entry: LandData) {
            textSoil.text = "Soil Type: ${entry.soil}"
            textElevation.text = "Elevation: ${entry.elevation}m"
//            textSuitability.text = "Suitability: ${entry.suitability}" // You need to have suitability in your LandData
            textRisk.text = "Risk: ${entry.risk}"  // You need to have risk in your LandData
//            textInfra.text = "Infra: ${entry.infrastructure}" // You need to have infrastructure in your LandData
            textDensity.text = "Density: ${entry.density} per km²"

            itemView.setOnClickListener { onClick(entry) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_land_data, parent, false) // Correct XML file
        return LandViewHolder(view)
    }

    override fun onBindViewHolder(holder: LandViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LandData>() {
            override fun areItemsTheSame(old: LandData, new: LandData) = old.id == new.id
            override fun areContentsTheSame(old: LandData, new: LandData) = old == new
        }
    }
}
