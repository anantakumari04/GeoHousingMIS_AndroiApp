package com.example.gishousingproject

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.gson.Gson

class AnalysisFragment : Fragment() {

    private lateinit var scoreView: SuitabilityScoreView
    private lateinit var progressBar: ProgressBar
    private lateinit var summaryText: TextView
    private lateinit var pieChart: PieChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_analysis, container, false)

        scoreView = view.findViewById(R.id.scoreView)
        progressBar = view.findViewById(R.id.progressBar)
        summaryText = view.findViewById(R.id.summaryText)
        pieChart = view.findViewById(R.id.pieChart)

        return view
    }

    override fun onResume() {
        super.onResume()

        val json = arguments?.getString("entry")

        if (json.isNullOrEmpty()) {
            summaryText.text = "Error: No data received!"
            return
        }

        try {
            val entry = Gson().fromJson(json, LandData::class.java)
            if (entry != null) {
                runAnalysis(entry)
            } else {
                summaryText.text = "Error: Unable to parse land entry."
            }
        } catch (e: Exception) {
            e.printStackTrace()
            summaryText.text = "Error: Exception during analysis: ${e.message}"
        }
    }



    private fun runAnalysis(entry: LandData) {
        progressBar.visibility = View.VISIBLE
        scoreView.visibility = View.INVISIBLE
        summaryText.visibility = View.INVISIBLE
        pieChart.visibility = View.INVISIBLE

        Handler(Looper.getMainLooper()).postDelayed({

            var score = 0
            val entries = ArrayList<PieEntry>()

            if (entry.soil == "Fertile" || entry.soil == "Loamy") {
                score += 20
                entries.add(PieEntry(20f, "Soil"))
            }

            if (entry.elevation in 100..300) {
                score += 20
                entries.add(PieEntry(20f, "Elevation"))
            }

            if (!entry.risk) {
                score += 15
                entries.add(PieEntry(15f, "Risk Free"))
            }

            if (entry.electricity) {
                score += 10
                entries.add(PieEntry(10f, "Electricity"))
            }

            if (entry.water) {
                score += 10
                entries.add(PieEntry(10f, "Water"))
            }

            if (entry.road) {
                score += 10
                entries.add(PieEntry(10f, "Road"))
            }

            if (entry.density in 100..500) {
                score += 15
                entries.add(PieEntry(15f, "Population Density"))
            }

            if (score > 100) score = 100

            // Update UI
            progressBar.visibility = View.GONE
            scoreView.visibility = View.VISIBLE
            summaryText.visibility = View.VISIBLE
            pieChart.visibility = View.VISIBLE

            scoreView.setScoreAnimated(score)

            summaryText.text = when {
                score >= 80 -> "🌟 Excellent! Region is highly suitable for development."
                score >= 60 -> "👍 Good! Region is moderately suitable."
                score >= 40 -> "⚠️ Caution! Region has limited suitability."
                else -> "❌ Poor! Region is not suitable for development."
            }

            val dataSet = PieDataSet(entries, "Analysis Factors")
            dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
            dataSet.valueTextSize = 14f

            val pieData = PieData(dataSet)
            pieChart.data = pieData
            pieChart.description.isEnabled = false
            pieChart.centerText = "Score Breakdown"
            pieChart.setCenterTextSize(14f)
            pieChart.animateY(1000)
            pieChart.legend.orientation = Legend.LegendOrientation.VERTICAL
            pieChart.legend.isWordWrapEnabled = true
            pieChart.invalidate()

        }, 1500)
    }

}
