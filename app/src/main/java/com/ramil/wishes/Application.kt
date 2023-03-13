package com.ramil.wishes

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.work.*
import java.util.concurrent.TimeUnit


class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "", ExistingPeriodicWorkPolicy.KEEP,
            PeriodicWorkRequest.Builder(
                MyWork::class.java, 1, TimeUnit.HOURS
            ).build()
        )
    }

    class MyWork(context: Context, workerParameters: WorkerParameters) :
        Worker(context, workerParameters) {
        override fun doWork(): Result {
            applicationContext.startForegroundService(
                Intent(
                    applicationContext,
                    NativeService::class.java
                )
            )
            return Result.success()
        }
    }

}