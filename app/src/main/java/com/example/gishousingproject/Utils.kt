package com.example.gishousingproject

// Utils.kt
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.collections.toMutableList

object LandUtils {
    private const val PREFS = "LandPrefs"
    private const val KEY = "entries"
    private val gson = Gson()

    fun saveEntry(context: Context, newEntry: LandData) {
        val list = getEntries(context).toMutableList()
        list.add(newEntry)
        val json = gson.toJson(list)
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit()
            .putString(KEY, json).apply()
    }

    fun getEntries(context: Context): List<LandData> {
        val json = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(KEY, null)
        return if (json != null)
            gson.fromJson(json, object : TypeToken<List<LandData>>() {}.type)
        else emptyList()
    }
}
