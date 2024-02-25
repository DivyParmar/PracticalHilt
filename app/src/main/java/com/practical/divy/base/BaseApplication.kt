package com.practical.divy.base

import android.content.Context
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : MultiDexApplication() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

    }


    companion object {
        private var instance: BaseApplication? = null
        fun getApplicationContext(): Context {
            return instance?.applicationContext!!
        }
    }
}