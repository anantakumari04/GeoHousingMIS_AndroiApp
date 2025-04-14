package com.example.gishousingproject

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var adapter: ViewPagerAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Home"
                1 -> "Analysis"
                2 -> "Settings"
                else -> "Tab"
            }
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                hideOverlayFragment()
                supportFragmentManager.popBackStack("overlay", FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        val prefs = getSharedPreferences("geo_prefs", MODE_PRIVATE)
        if (prefs.getBoolean("isFirstTime", true)) {
            Toast.makeText(this, "Welcome to GeoHousing!", Toast.LENGTH_SHORT).show()
            prefs.edit().putBoolean("isFirstTime", false).apply()
        }

        val file = File(filesDir, "land_data.json")
        if (!file.exists()) {
            file.writeText("{\"soil\": \"fertile\"}")
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                101
            )
        }
    }

    fun showOverlayFragment(fragment: Fragment) {
        findViewById<View>(R.id.viewPager).visibility = View.GONE
        findViewById<View>(R.id.main_fragment_overlay).visibility = View.VISIBLE

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_overlay, fragment)
            .addToBackStack("overlay")
            .commit()
    }

    fun hideOverlayFragment() {
        findViewById<View>(R.id.viewPager).visibility = View.VISIBLE
        findViewById<View>(R.id.main_fragment_overlay).visibility = View.GONE
    }

    override fun onBackPressed() {
        val overlay = findViewById<View>(R.id.main_fragment_overlay)
        if (overlay.visibility == View.VISIBLE) {
            hideOverlayFragment()
            supportFragmentManager.popBackStack("overlay", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        } else {
            super.onBackPressed()
        }
    }
}
