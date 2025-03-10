package com.example.boschcompose1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class DiceRollerActivity : ComponentActivity() {
     val REQUEST_SELECT_CONTACT = 1
     val REQUEST_SELECT_PHONE_NUMBER = 1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()
        setContent {
            DiceRollerApp()
        }
    }



    @Composable
    fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
        var result by remember { mutableStateOf(1) }
        val imageResource = when (result) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        Column (
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(imageResource),
                contentDescription = "1"

            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {result = (1..6).random() }) {
                Text(stringResource(R.string.roll))
            }
            Button(onClick = {
               // startTimer("android",60)
                selectContact()
            }) {
                Text(text = "launchCalendar")

            }
            ButtonClickExample()

        }
    }

    @Composable
    fun ButtonClickExample(){
        val context = LocalContext.current

        var resultText by remember {
            mutableStateOf("no result yet")
        }
        var launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            activityResult ->  if(activityResult.resultCode == RESULT_OK){
                val data = activityResult.data?.getStringExtra("pn")
            resultText = data ?: "no data"
            }

        }

        Button(onClick = {
            val cIntent = Intent(context,ContactActivity::class.java)
            launcher.launch(cIntent)
        }) {
            Text(text = "startactivityforresult")
        }
        Text(text = resultText)

    }

    fun startTimer(message: String, seconds: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_TIMER).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            putExtra(AlarmClock.EXTRA_LENGTH, seconds)
            putExtra(AlarmClock.EXTRA_SKIP_UI, true)
        }
        //if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
       // }
    }

    fun selectContact() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = ContactsContract.Contacts.CONTENT_TYPE
        }
     //   if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_SELECT_CONTACT)
       // }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { if (requestCode == REQUEST_SELECT_PHONE_NUMBER && resultCode == Activity.RESULT_OK) {
        // Get the URI and query the content provider for the phone number.
        val contactUri: Uri = data?.data!!  //url --pointing to the db table containing contacts
        val projection: Array<String> = arrayOf(Phone.NUMBER)
        contentResolver.query(contactUri, projection, null, null, null).use { cursor ->
            // If the cursor returned is valid, get the phone number.
            if (cursor!!.moveToFirst()) {
                val numberIndex = cursor?.getColumnIndex(Phone.NUMBER)

                val number = cursor?.getString(numberIndex!!)
                // Do something with the phone number.
                Log.i("diceroller","phno="+number)

            }
        }
    }
    }





    @Preview
    @Composable
    fun DiceRollerApp() {
        DiceWithButtonAndImage(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center))

    }
}


