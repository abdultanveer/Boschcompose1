package com.example.boschcompose1

import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar


class XmlActivity : AppCompatActivity() {
    var TAG = XmlActivity::class.java.simpleName
    lateinit var cpListView:ListView

    private val SMS_PERMISSION_CODE = 101 //request code

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml)
        cpListView = findViewById(R.id.listView)

        //content resolver --bring up the db -- intent - activity via intent filter table
       if( checkSmsPermission()) {
          setResultListView()
       }
        else {
            requestSmsPermission()
        }

    }


    private fun checkSmsPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestSmsPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(
            android.Manifest.permission.READ_SMS),
            SMS_PERMISSION_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setResultListView()
            } else {
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setResultListView() {
        val uriSms = Uri.parse("content://sms/inbox")

        val cursor: Cursor? = getContentResolver().query(uriSms, null, null, null, null)

        //select * from uri
        var fromColumns = arrayOf("address", "body")
        var toTextViews = intArrayOf(android.R.id.text1, android.R.id.text2)

        var myAdapter: SimpleCursorAdapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_2, cursor, fromColumns, toTextViews
        )
        cpListView.adapter = myAdapter    }

    fun clickHandler(view: View) {
        var view:ConstraintLayout = findViewById(R.id.conslayout)

        Snackbar.make(view,"button clicked",Snackbar.LENGTH_SHORT).show()
        Log.i(TAG,"in clickhandler")
        var intent = Intent(this,MainActivity::class.java)
        startActivity(intent)

    }
}