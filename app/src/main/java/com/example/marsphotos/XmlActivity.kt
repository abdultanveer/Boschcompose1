package com.example.marsphotos

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.provider.AlarmClock
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.boschcompose1.R
import com.google.android.material.snackbar.Snackbar

class XmlActivity : AppCompatActivity() {
    var TAG = XmlActivity::class.java.simpleName
    var requestCodeContact = 123
    var requestCodePhot = 123
    lateinit var tvResult:TextView

   // var iRemoteService: IMyAidlInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml)
        tvResult = findViewById(R.id.tvResult)

    }

    override fun onStart() {
        super.onStart()
       // registerReceiver()
    }

    override fun onStop() {
        super.onStop()
        //unregisterReceiver()
    }

    fun clickHandler(view: View) {
        var view:ConstraintLayout = findViewById(R.id.conslayout)

        Snackbar.make(view,"button clicked",Snackbar.LENGTH_SHORT).show()
        Log.i(TAG,"in clickhandler")
        var intent = Intent(this, MainActivity::class.java)
        intent.putExtra("name","abdul")
        startActivity(intent)

    }

    fun startDialer(view: View) {
//       var dIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:123456789"))
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

    fun getContact(view: View) {
        //xml activity is  a parent and contactactivity is the child
        var cIntent = Intent(this, ContactActivity::class.java)
        startActivityForResult(cIntent,requestCodeContact)
    //in wa from the same activity you can go to diff  apps ie contacts/camera/location
        //when you return back, how can wa activity differentiate b/w what kind of data you're bringing.. you return back to the same point ie line no 64
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, resintent: Intent?) {
        super.onActivityResult(requestCode, resultCode, resintent)

        //what was the request code -- with what kind of data you're returning
        if(requestCode == requestCodeContact && resultCode == RESULT_OK){

            var phno = resintent?.getStringExtra("pn")
            tvResult.text = phno
        }


    }

    fun launchCalendar(view: View) {
        var calIntent = Intent("ineed.water")
        startActivity(calIntent)
    }

    fun startAidlservice(view: View) {
            //aidl-4
        val intent = Intent("ineed.addition.bosch")
        val pack = IMyAidlInterface::class.java.`package`
        intent.setPackage(pack.toString())
        intent.setPackage(pack.name)
        bindService(intent,connection, BIND_AUTO_CREATE)
    }

    val connection = object :ServiceConnection{
        override fun onServiceConnected(p0: ComponentName?, aidlBinder: IBinder?) {
            Log.i("clientActivity", "client activity  connected to service")
            val iRemoteService = IMyAidlInterface.Stub.asInterface(aidlBinder)
           val result =  iRemoteService.add(10,15)
            Log.i("clientActivity","result is--$result")
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            TODO("Not yet implemented")
        }
    }
}

//<activity   Manifest of other app
//android:name=".CalendarActivity"
//android:exported="true" >
//<intent-filter>
//<action android:name="ineed.water"/>
// <category android:name="android.intent.category.DEFAULT"/>
//</intent-filter>
//</activity>