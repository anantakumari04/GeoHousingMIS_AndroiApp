package com.example.gishousingproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class HomeFragment : Fragment() {

    private lateinit var landRecyclerView: RecyclerView
    private lateinit var adapter: LandDataAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // RecyclerView setup
        landRecyclerView = view.findViewById(R.id.recyclerViewLandData)
        landRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = LandDataAdapter { selectedEntry -> showAnalysis(selectedEntry) }
        landRecyclerView.adapter = adapter

        // Help Button setup
        val helpButton = view.findViewById<Button>(R.id.helpButton)
        helpButton.setOnClickListener {
            showHelpDialog()
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        adapter.submitList(LandUtils.getEntries(requireContext()))
    }

    private fun showAnalysis(entry: LandData) {
        val fragment = AnalysisFragment().apply {
            arguments = Bundle().apply {
                putString("entry", Gson().toJson(entry))
            }
        }

        (requireActivity() as MainActivity).showOverlayFragment(fragment)
    }

    private fun showHelpDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("How to Use the App")
        builder.setMessage(
            "1. View saved land entries.\n" +
                    "2. Tap on an entry to see detailed analysis.\n" +
                    "3. Use the analysis to determine housing suitability.\n" +
                    "4. Contact local planners for implementation guidance."
        )
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }
}
