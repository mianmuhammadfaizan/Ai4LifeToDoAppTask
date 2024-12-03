package com.toDoApp

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class ToDoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        scheduleApiWork()
        val periodicWorkRequest = PeriodicWorkRequestBuilder<ApiWorker>(1, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "PeriodicAPIWorker",
            ExistingPeriodicWorkPolicy.UPDATE,
            periodicWorkRequest
        )
    }

    private fun scheduleApiWork() {
        val workRequest = OneTimeWorkRequestBuilder<ApiWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(this).enqueueUniqueWork(
            "APIWorker",
            ExistingWorkPolicy.REPLACE,
            workRequest
        )
    }
}