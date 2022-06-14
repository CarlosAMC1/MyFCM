package firebase.app.myfcm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import firebase.app.myfcm.service.MyFirebaseMessagingService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = intent
        val message = intent.getStringExtra("ServiceFCM")
        if (!message.isNullOrEmpty()){
            AlertDialog.Builder(this)
                .setTitle("Notification")
                .setMessage(message)
                .setPositiveButton("ok",{dialog,which->}).show()

        }
    }
}