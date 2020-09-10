package com.example.gadproject.util

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

object Helper {


    fun getDeviceMetrics(context: Context): DisplayMetrics {
        val metrics = DisplayMetrics()
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        display.getMetrics(metrics)
        return metrics
    }

}