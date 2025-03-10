package com.example.boschcompose1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactActivity : AppCompatActivity() {
    lateinit var etContact:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        etContact = findViewById(R.id.etContact)

    }

    fun contactSelected(view: View) {
        var  phno = etContact.text.toString()
        var resintent = Intent()
        resintent.putExtra("pn",phno)
        setResult(RESULT_OK,resintent)  //result code --- smell--flag-data is ok to consume
        finish()  //destroyed
    }
}