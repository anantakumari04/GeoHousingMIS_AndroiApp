//package com.example.gishousingproject
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//
//class AnalysisActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_analysis)
//
//        if (savedInstanceState == null) {
//            val entryJson = intent.getStringExtra("entry")
//            val fragment = AnalysisFragment().apply {
//                arguments = Bundle().apply {
//                    putString("entry", entryJson)
//                }
//            }
//
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.analysisFragmentContainer, fragment)
//                .commit()
//        }
//    }
//}
