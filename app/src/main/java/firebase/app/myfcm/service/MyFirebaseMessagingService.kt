package firebase.app.myfcm.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import firebase.app.myfcm.MainActivity
import firebase.app.myfcm.R

class MyFirebaseMessagingService:FirebaseMessagingService() {
    val TAG = "ServiceFCM"
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d(TAG,"Desde: " + message.from)
        Log.d(TAG, "Cuerpo de la Notificacion: " + message.notification!!.body)

        sendNotification(message)
        val intent = Intent(this@MyFirebaseMessagingService,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra(TAG,message.notification!!.body)
        startActivity(intent)
    }

    private fun sendNotification(message: RemoteMessage){
        val intent = Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val REQUEST = 0
        val pendingIntent = PendingIntent.getActivity(this,REQUEST,
        intent,PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this)
            .setContentText(message.notification!!.body)
            .setAutoCancel(true)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(TAG,0, notificationBuilder.build())
    }


}