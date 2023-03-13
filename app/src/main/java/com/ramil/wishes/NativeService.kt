package com.ramil.wishes

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import androidx.core.app.NotificationCompat

class NativeService : Service() {
    companion object {
        private const val WISHES = "Wishes"
        private const val CHANNEL = 27072005
    }

    private lateinit var broadcast: NotificationBroadcast

    override fun onCreate() {
        super.onCreate()
        val nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        nm.createNotificationChannel(
            NotificationChannel(
                WISHES,
                WISHES,
                NotificationManager.IMPORTANCE_NONE
            )
        )

        startForeground(27072005, NotificationCompat.Builder(
            this,
            WISHES
        ).apply {
            setChannelId(WISHES)
            setContentTitle("Утренние пожелания")
            setTicker("Утренние пожелания")
            setContentInfo("")
            setContentIntent(null)
            setSmallIcon(R.drawable.icon)
            setAutoCancel(true)
        }.build())

        val filter = IntentFilter(Intent.ACTION_SCREEN_ON)
        broadcast = NotificationBroadcast()
        registerReceiver(broadcast, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcast)
        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager)
            .deleteNotificationChannel(WISHES)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

}