package com.example.admin

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class GlobalClass: Application() {
    companion object {
        lateinit var context: Context
        fun applicationContext(): Context {
            return context
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        //turn off dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

}