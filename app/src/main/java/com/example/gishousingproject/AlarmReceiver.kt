package com.example.gishousingproject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.gishousingproject.utils.NotificationHelper

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val helper = NotificationHelper(context)
        helper.showNotification("Analysis Reminder", "Don't forget to check suitability data.")
    }
}