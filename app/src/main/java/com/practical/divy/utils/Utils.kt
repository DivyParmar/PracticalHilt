package com.practical.divy.utils

import android.app.Activity
import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.ConnectivityManager
import android.net.Uri
import android.util.DisplayMetrics
import android.view.View
import android.widget.RelativeLayout
import com.practical.divy.base.BaseApplication

import kotlin.math.roundToInt


class Utils {

    companion object {

        fun callNow(activity: Activity,phone:String){
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phone")
            activity.startActivity(intent)
        }

        fun launchSms(context: Context,mobileNumber:String,message:String){
            val smsIntent = Intent(Intent.ACTION_VIEW)
            smsIntent.type = "vnd.android-dir/mms-sms"
            smsIntent.putExtra("address", mobileNumber)
            smsIntent.putExtra("sms_body", message)
            context.startActivity(smsIntent)
        }


        fun createDrawableFromView(context: Context, view: View): Bitmap? {
            val displayMetrics = DisplayMetrics()
            (context as Activity).windowManager.defaultDisplay
                .getMetrics(displayMetrics)
            view.setLayoutParams(
                RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
                )
            )
            view.measure(100, 100)
            view.layout(
                0, 0, 100,
                100
            )
            view.buildDrawingCache()
            val bitmap = Bitmap.createBitmap(
                200,
                200, Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            view.draw(canvas)
            return bitmap
        }

        fun isDarkMode(context: Context): Boolean {
            val darkModeFlag =
                context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            return darkModeFlag == Configuration.UI_MODE_NIGHT_YES
        }

        fun convertDpToPixel(dp: Float, context: Context): Float {
            return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }
        fun convertPixelsToDp(px: Float, context: Context): Float {
            return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }
        fun isAppOnForeground(context: Context): Boolean {
            val activityManager =
                context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val appProcesses = activityManager.runningAppProcesses ?: return false
            for (appProcess in appProcesses) {
                if (appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName == context.packageName) {
                    return true
                }
            }
            return false
        }


        fun getScreenHeightInDP(context: Context): Int {
            val displayMetrics: DisplayMetrics = context.resources.displayMetrics
            val screenHeightInDP = displayMetrics.heightPixels / displayMetrics.density
            return Math.round(screenHeightInDP)
        }





        fun pxToDp(px: Int, context: Context): Int {
            val displayMetrics: DisplayMetrics = context.resources.displayMetrics
            return (px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
        }

        fun isNetworkAvailable(): Boolean {
            val cm =
                BaseApplication.getApplicationContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return (netInfo != null && netInfo.isConnectedOrConnecting
                    && cm.activeNetworkInfo?.isAvailable == true
                    && cm.activeNetworkInfo?.isConnected == true)
        }


        fun openBrowser(context: Context, url: String) {
            var url = url
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://$url"
            }
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(Intent.createChooser(intent, "Choose browser"))
        }
    }
}