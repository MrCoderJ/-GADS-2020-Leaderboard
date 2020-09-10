package com.example.gadproject.ui.widgets

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import com.example.gadproject.R
import com.example.gadproject.util.Helper
import kotlinx.android.synthetic.main.dialog_message.*
import kotlin.math.roundToInt

class ErrorMessageDialog(ctx: Context, msg: String): Dialog(ctx, R.style.ModalTheme) {

    init {
        setContentView(R.layout.dialog_error_message)
        val params = window!!.attributes
        params.width = (Helper.getDeviceMetrics(context).widthPixels * 0.8).roundToInt()
        window!!.attributes = params as WindowManager.LayoutParams

        dialog_title.text = msg
    }
    fun setTitle(title: String): ErrorMessageDialog{
        dialog_title.text = title
        return this
    }
}