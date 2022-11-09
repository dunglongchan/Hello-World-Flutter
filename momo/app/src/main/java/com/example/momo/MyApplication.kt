package com.example.momo

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.os.Bundle
import android.os.StrictMode
import android.text.TextUtils
import android.util.DisplayMetrics
import java.util.*


class MyApplication : Application(), Application.ActivityLifecycleCallbacks {

    override fun attachBaseContext(context: Context?) {
        super.attachBaseContext(context)
    }

    override fun onActivityCreated(activity: Activity, p1: Bundle?) {
        setLocale(activity, getPreLanguage(activity))
    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
        //* For sharing files
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
    }

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

        fun getSelectedImagesList(): ArrayList<Bitmap> {
            return ArrayList()
        }

        fun showToolTip(mContext: Context): Boolean {
            val preferences: SharedPreferences =
                mContext.getSharedPreferences(mContext.packageName, Context.MODE_MULTI_PROCESS)
            return preferences.getBoolean("KEY_TOOLTIP", true)
        }

        fun setShowToolTip(context: Context, show: Boolean) {
            val preferences: SharedPreferences =
                context.getSharedPreferences(context.packageName, Context.MODE_MULTI_PROCESS)
            preferences.edit().putBoolean("KEY_TOOLTIP", show).apply()
        }

        var min_pos: Int = Int.MAX_VALUE
    }
}
