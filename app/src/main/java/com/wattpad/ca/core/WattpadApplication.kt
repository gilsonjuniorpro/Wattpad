package com.wattpad.ca.core

import android.app.Activity
import android.app.Application
import com.jarvis.ca.Mark

class WattpadApplication : Application() {

    companion object {
        fun showError(message: String) {
            Mark.showAlertError(activity, "${message}")
        }

        fun showSuccess(message: String) {
            Mark.showAlertSuccess(activity, "${message}")
        }

        @get:Synchronized
        lateinit var initializer:WattpadApplication

        lateinit var activity: Activity
    }

    override fun onCreate() {
        super.onCreate()
        initializer = this
    }
}