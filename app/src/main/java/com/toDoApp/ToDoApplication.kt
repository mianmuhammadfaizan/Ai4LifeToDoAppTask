package com.toDoApp

import android.app.Application
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ToDoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize your DI framework here if needed
        scheduleApiWorker(applicationContext) // Schedule the worker
    }
}