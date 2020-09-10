package com.example.gadproject.ui.widgets

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import com.example.gadproject.R
import com.example.gadproject.util.Helper
import kotlinx.android.synthetic.main.confirm_dialog.*
import kotlin.math.roundToInt

class ConfirmDialog(ctx: Context, title: String): Dialog(ctx, R.style.ModalTheme) {

    init {
        setContentView(R.layout.confirm_dialog)
        val params = window!!.attributes
        params.width = (Helper.getDeviceMetrics(context).widthPixels * 0.8).roundToInt()
        window!!.attributes = params as WindowManager.LayoutParams

        dialog_title.text = title

        btn_close.setOnClickListener {

            dismiss()
        }
        btn_accept.setOnClickListener {
            dismiss()
        }

    }
}