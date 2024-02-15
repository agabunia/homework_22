package com.example.homework_22.presentation.service

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log.d
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.homework_22.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotificationService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)

    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
//        d("fetching_message", "message: ${message.data}")
//
//        val data = message.data
//        showNotification(id = data["id"] ?: "", title = data["title"] ?: "", description = data["description"] ?: "")
    }



//    private fun showNotification(id: String, title: String, description: String) {
//        val notification = NotificationCompat.Builder(applicationContext, "homework_channel")
//            .setContentTitle(title)
//            .setContentText(description)
//            .setSmallIcon(R.drawable.notification_icon)
//            .build()
//
//        if (ContextCompat.checkSelfPermission(
//                applicationContext,
//                Manifest.permission.POST_NOTIFICATIONS
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            NotificationManagerCompat.from(applicationContext)
//                .notify(id.toInt(), notification)
//        }
//    }
}