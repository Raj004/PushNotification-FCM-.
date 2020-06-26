package com.example.pushnotification

import androidx.appcompat.app.AppCompatActivity

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.text.format.Formatter
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import com.google.firebase.messaging.FirebaseMessaging

import org.w3c.dom.Text

import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.util.Enumeration

class MainActivity : AppCompatActivity() {
    private var textView: TextView? = null
    private var btn_sub: Button? = null
    private var btn_unsub: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("Token", " " + FirebaseInstanceId.getInstance())
        textView = findViewById<View>(R.id.get_tv) as TextView
        btn_sub = findViewById<View>(R.id.sub_btn) as Button
        btn_unsub = findViewById<View>(R.id.unsub_btn) as Button
        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener { task -> Log.d(TAG, "onComplete:" + task.result!!.token) }
        if (intent != null && intent.hasExtra("key1")) {
            textView!!.text = ""
            for (key in intent.extras!!.keySet()) {
                Log.d(TAG, "onCreate: Key: " + key + "Data " + intent.extras!!.getString(key))
                textView!!.append(intent.extras!!.getString(key)!! + "\n")
            }
        }
        btn_sub!!.setOnClickListener {
            FirebaseMessaging.getInstance().subscribeToTopic("Topic-Cricket")
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful)
                            Toast.makeText(this@MainActivity, "Topic Subscribe", Toast.LENGTH_SHORT).show()
                        else
                            Toast.makeText(this@MainActivity, "Subscription failed", Toast.LENGTH_SHORT).show()
                    }
        }
        btn_unsub!!.setOnClickListener {
            FirebaseMessaging.getInstance().unsubscribeFromTopic("Topic-Cricket")
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful)
                            Toast.makeText(this@MainActivity, "Topic UnSubscribe", Toast.LENGTH_SHORT).show()
                        else
                            Toast.makeText(this@MainActivity, "Action failed", Toast.LENGTH_SHORT).show()
                    }
        }
    }

    companion object {
        val TAG = "MyTag"
    }

}
