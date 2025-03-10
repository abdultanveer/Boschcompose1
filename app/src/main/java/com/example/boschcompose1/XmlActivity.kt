package com.example.boschcompose1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class XmlActivity : AppCompatActivity() {
    var TAG = XmlActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml)

    }

    fun clickHandler(view: View) {
        var view:ConstraintLayout = findViewById(R.id.conslayout)

        Snackbar.make(view,"button clicked",Snackbar.LENGTH_SHORT).show()
        Log.i(TAG,"in clickhandler")
        var intent = Intent(this,MainActivity::class.java)
        intent.putExtra("name","abdul")
        startActivity(intent)

    }

    fun startDialer(view: View) {
//        var dIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:123456789"))
//        startActivity(dIntent)

        createAlarm("bosch",9,48)
    }



    fun createAlarm(message: String, hour: Int, minutes: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}