// LandData.kt
package com.example.gishousingproject

// LandEntry.kt
data class LandData(
    val id : Long,

    val soil: String,
    val elevation: Int,
    val risk: Boolean,
    val electricity: Boolean,
    val water: Boolean,
    val road: Boolean,
    val density: Int
)


