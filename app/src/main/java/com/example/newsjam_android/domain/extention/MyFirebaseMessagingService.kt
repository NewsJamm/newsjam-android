package com.example.newsjam_android.domain.extention

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import com.example.newsjam_android.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    @Override
    override fun onMessageReceived(message: RemoteMessage) {
        if (message.data.isNotEmpty()) {
            sendNotification(message)
        }
    }

    private fun sendNotification(message: RemoteMessage) {
        val title = message.notification?.title
        val msg = message.notification?.body

        val CHANNEL_ID = "firebase_channel_id"
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val CHANNEL_NAME = "firebase_channel";
        val CHANNEL_DESCRIPTION = "메세지 수신";
        val importance = NotificationManager.IMPORTANCE_HIGH;
        val mChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
        with(mChannel) {
            description = CHANNEL_DESCRIPTION
            enableLights(true)
            enableVibration(true)
            lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            manager.createNotificationChannel(mChannel)
        }

        val builder = Notification.Builder(this, CHANNEL_ID)
        with(builder) {
            setSmallIcon(R.mipmap.ic_launcher)
            setAutoCancel(true)
            setWhen(System.currentTimeMillis())
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle(title)
            setContentText(msg)
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                builder.setContentTitle(title);
                builder.setContentText(msg);
            }
            manager.notify(0, builder.build())
        }
    }

    @Override
    override fun onNewToken(token: String) {
        Log.d("토큰", "token =$token")
        super.onNewToken(token)
    }
}