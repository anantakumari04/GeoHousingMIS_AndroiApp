package com.example.gishousingproject

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.gishousingproject.R

object CustomToast {

    @SuppressLint("MissingInflatedId")
    fun show(context: Context, message: String) {
        val inflater = LayoutInflater.from(context)
        val layout: View = inflater.inflate(R.layout.custom_toast, null)

        layout.findViewById<TextView>(R.id.toastText).text = message

        val toast = Toast(context)
        toast.duration = Toast.LENGTH_LONG
        toast.view = layout
        toast.show()
    }
}
