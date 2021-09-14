package com.app.core.base.dialog

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import com.app.core.R

class LoaderDialog(context: Context) : AlertDialog(context) {

    override fun show() {
        super.show()
        setContentView(R.layout.dialog_progress)
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onBackPressed() {
        cancel()
        super.onBackPressed()
    }

}