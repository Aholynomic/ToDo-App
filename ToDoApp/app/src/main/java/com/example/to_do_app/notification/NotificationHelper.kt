package com.example.to_do_app.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.to_do_app.R

private const val CHANNEL_ID = "test"
private const val CHANNEL_NAME = "Event_App"
private const val NOTIFICATION_ID = 1

/**
 * Intense Coder
 * https://intensecoder.com/push-notifications-in-android-using-kotlin/
 */
class NotificationHelper {

    fun sendNotification(context: Context, title: String, body: String) {
        val manager = NotificationManagerCompat.from(context)

        // check for build version and build a channel if greater than API 26
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            manager.createNotificationChannel(createNotificationChannel())

        // assign values to the notification from the form submission
        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(body)

        // show the notification
        manager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(): NotificationChannel {
        return NotificationChannel(
                CHANNEL_ID, CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
        )
    }
}