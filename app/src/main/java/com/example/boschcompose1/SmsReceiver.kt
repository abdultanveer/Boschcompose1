package com.example.boschcompose1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log

class SmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.i("SMSRECEIVER","abdul-bosch-u received a message")
//        var intent = Intent(context,DiceRollerActivity::class.java)
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        context.startActivity(intent)
        if (intent?.action.equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            val items: Array<SmsMessage> = Telephony.Sms.Intents.getMessagesFromIntent(intent)
            for (item in items) {Log.d("Smsreceiver", item.messageBody+item.originatingAddress)}
        }
    }
}