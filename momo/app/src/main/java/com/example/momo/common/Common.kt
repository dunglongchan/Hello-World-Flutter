package com.example.momo.common

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.net.wifi.WifiManager
import android.text.TextUtils
import android.util.DisplayMetrics
import android.widget.TextView
import com.codemybrainsout.ratingdialog.RatingDialog
import com.example.momo.R
import com.example.momo.model.ConversationModel
import com.example.momo.model.TransactionModel
import com.example.momo.model.UserModel
import com.google.gson.Gson
import com.vapp.admoblibrary.ads.AdmodUtils
import com.vapp.admoblibrary.ads.AppOpenManager
import java.io.IOException
import java.util.*

class Common {
    companion object {

        fun getPreLanguage(mContext: Context): String? {
            val preferences: SharedPreferences =
                mContext.getSharedPreferences(mContext.packageName, Context.MODE_MULTI_PROCESS)
            return preferences.getString("KEY_LANGUAGE", "en")
        }

        fun setPreLanguage(context: Context, language: String?) {
            if (TextUtils.isEmpty(language)) return
            val preferences: SharedPreferences =
                context.getSharedPreferences(context.packageName, Context.MODE_MULTI_PROCESS)
            preferences.edit().putString("KEY_LANGUAGE", language).apply()
        }

        fun getPreLanguageflag(mContext: Context): Int {
            val preferences: SharedPreferences =
                mContext.getSharedPreferences(mContext.packageName, Context.MODE_MULTI_PROCESS)
            return preferences.getInt("KEY_FLAG", R.drawable.english)
        }

        fun setPreLanguageflag(context: Context, flag: Int) {
            val preferences: SharedPreferences =
                context.getSharedPreferences(context.packageName, Context.MODE_MULTI_PROCESS)
            preferences.edit().putInt("KEY_FLAG", flag).apply()
        }

        fun getFirstOpen(mContext: Context): Boolean {
            val preferences: SharedPreferences =
                mContext.getSharedPreferences(mContext.packageName, Context.MODE_MULTI_PROCESS)
            return preferences.getBoolean("KEY_OPEN", true)
        }

        fun setPosition(context: Context, open: Int) {
            val preferences: SharedPreferences =
                context.getSharedPreferences(context.packageName, Context.MODE_MULTI_PROCESS)
            preferences.edit().putInt("KEY_POSITION", open).apply()
        }

        fun getPosition(mContext: Context): Int {
            val preferences: SharedPreferences =
                mContext.getSharedPreferences(mContext.packageName, Context.MODE_MULTI_PROCESS)
            return preferences.getInt("KEY_POSITION", 0)
        }

        fun setFirstOpen(context: Context, open: Boolean) {
            val preferences: SharedPreferences =
                context.getSharedPreferences(context.packageName, Context.MODE_MULTI_PROCESS)
            preferences.edit().putBoolean("KEY_OPEN", open).apply()
        }

        fun setLocale(context: Context, lang: String?) {
            val myLocale = Locale(lang)
            setPreLanguage(context, lang)
            val res: Resources = context.resources
            val dm: DisplayMetrics = res.displayMetrics
            val conf: Configuration = res.configuration
            conf.setLocale(myLocale)
            res.updateConfiguration(conf, dm)
        }

        fun createKey(cameraId: Int, postfix: String): String? {
            return cameraId.toString() + postfix
        }

        fun createKey(cameraId: Int, postfix: String, index: Int): String? {
            return cameraId.toString() + postfix + index
        }

        fun isNetworkWorking(context: Context): Boolean {
            return AdmodUtils.getInstance().isNetworkConnected(context)
        }

        fun getValidateUser(context: Context): String {
            val rate: SharedPreferences =
                context.getSharedPreferences("save_user_state", Context.MODE_PRIVATE)
            return rate.getString("save_user_state", "").toString()
        }


        fun setValidateUser(context: Context, string: String) {
            val rate: SharedPreferences =
                context.getSharedPreferences("save_user_state", Context.MODE_PRIVATE)
            rate.edit().putString("save_user_state", string).apply()
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

        fun showRate(context: Context) {
            val ratingDialog = RatingDialog.Builder(context as Activity)
                .session(1)
                .date(1)
                .setNameApp(context.getString(R.string.app_name))
                .setIcon(R.mipmap.ic_launcher)
                .setEmail("dunglccl@icloud.com")
                .setOnlickRate {
                    AppOpenManager.getInstance()
                        .disableAppResumeWithActivity((context as Activity)::class.java)
                }
                .isShowButtonLater(true)
                .isClickLaterDismiss(true)
                .setTextButtonLater(context.getString(R.string.later))
                .ratingButtonColor(R.color.primary_color)
                .build()
            ratingDialog.setCanceledOnTouchOutside(false)
            ratingDialog.show()
        }

        fun modelToJson(obj: Any?): String? {
            return Gson().toJson(obj)
        }

        fun jsonToModel(str: String?, i: Int): Any? {
            val gson = Gson()
            try {
                return when (i) {
                    Constant.TRANSACTION_MODEL -> {
                        gson.fromJson(str, TransactionModel::class.java)
                    }
                    Constant.CONVERSATION_MODEL -> {
                        gson.fromJson(str, ConversationModel::class.java)
                    }
                    else -> {
                        gson.fromJson(str, UserModel::class.java)

                    }
                }
            } catch (e: IOException) {
                print(e.message)
                return null
            }

        }
    }
}