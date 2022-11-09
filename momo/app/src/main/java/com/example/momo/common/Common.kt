package com.example.momo.common

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.wifi.WifiManager
import android.widget.TextView
import com.example.momo.R
import com.vapp.admoblibrary.ads.AdmodUtils

class Common {
    companion object {
        fun isNetworkWorking(context: Context): Boolean {
            return AdmodUtils.getInstance().isNetworkConnected(context)
        }

        fun openRequireNetworkDialog(context: Context) {
            val dialog = Dialog(context) // Context, this, etc.
            dialog.setCancelable(true)
            dialog.setCanceledOnTouchOutside(true)
            dialog.setContentView(R.layout.dialog_no_internet)
            if (dialog.window != null) {
                dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
                dialog.window!!.setLayout(-1, -2)
            }
            val textView: TextView = dialog.findViewById<TextView>(R.id.tv_connect)
            textView.setOnClickListener {
                context.startActivity(Intent(WifiManager.ACTION_PICK_WIFI_NETWORK))
                dialog.dismiss()
            }

            dialog.show()
        }

    }
}