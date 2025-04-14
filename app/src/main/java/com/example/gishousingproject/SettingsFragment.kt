package com.example.gishousingproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

class SettingsFragment : Fragment() {

    private lateinit var soilSpinner: Spinner
    private lateinit var elevationSeekBar: SeekBar
    private lateinit var riskSwitch: Switch
    private lateinit var checkElectricity: CheckBox
    private lateinit var checkWater: CheckBox
    private lateinit var checkRoad: CheckBox
    private lateinit var elevationValue: TextView
    private lateinit var densitySeekBar: SeekBar
    private lateinit var densityValue: TextView
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        // UI Init
        soilSpinner = view.findViewById(R.id.spinnerSoil)
        elevationSeekBar = view.findViewById(R.id.seekBarElevation)
        riskSwitch = view.findViewById(R.id.switchRisk)
        checkElectricity = view.findViewById(R.id.checkElectricity)
        checkWater = view.findViewById(R.id.checkWater)
        checkRoad = view.findViewById(R.id.checkRoad)
        elevationValue = view.findViewById(R.id.textElevationValue)
        densitySeekBar = view.findViewById(R.id.seekBarDensity)
        densityValue = view.findViewById(R.id.textDensityValue)
        saveButton = view.findViewById(R.id.buttonSaveSettings)

        // Soil Spinner
        val soilTypes = arrayOf("Fertile", "Sandy", "Clay", "Loamy")
        val adapter = ArrayAdapter(requireContext(), R.layout.spinner_item, soilTypes)
        adapter.setDropDownViewResource(R.layout.spinner_item)
        soilSpinner.adapter = adapter



        // Elevation
        elevationSeekBar.max = 500
        elevationSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                elevationValue.text = "$progress m"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Density
        densitySeekBar.max = 1000
        densitySeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                densityValue.text = "$progress /km²"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Save Button Click
        saveButton.setOnClickListener {
            val entry = LandData(
                id = System.currentTimeMillis(),
                soil = soilSpinner.selectedItem.toString(),
                elevation = elevationSeekBar.progress,
                risk = riskSwitch.isChecked,
                electricity = checkElectricity.isChecked,
                water = checkWater.isChecked,
                road = checkRoad.isChecked,
                density = densitySeekBar.progress

            )

            LandUtils.saveEntry(requireContext(), entry)

            Toast.makeText(requireContext(), "✅ Land entry saved!", Toast.LENGTH_SHORT).show()

            (activity as? MainActivity)?.findViewById<ViewPager2>(R.id.viewPager)?.currentItem = 0

        }



        return view
    }
}
