package com.saharatt593.dwmc2200

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class DetailActivity : AppCompatActivity() {

    var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            //  Received a barcode scan
            try {
                displayScanResult(intent)
            } catch (e: Exception) {
                //  Catch if the UI does not exist when we receive the broadcast... this is not designed to be a production app
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setConfigBroadcastReceiver()
    }

    override fun onResume() {
        super.onResume()
        val i = Intent()

        i.action = resources.getString(R.string.datawedge_intent_action)
        i.putExtra(resources.getString(R.string.datawedge_switch_profile), resources.getString(R.string.datawedge_profile))
        sendBroadcast(i)

        i.action = resources.getString(R.string.datawedge_intent_action)
        i.putExtra(resources.getString(R.string.datawedge_enable_datawedge), true)
        i.putExtra(resources.getString(R.string.datawedge_scanner_input_plugin), resources.getString(R.string.datawedge_enable_plugin))
        sendBroadcast(i)
    }

    private fun setConfigBroadcastReceiver(){
        val filter = IntentFilter()
        filter.addAction(resources.getString(R.string.datawedge_intent_broadcast_action))
        filter.addCategory(Intent.CATEGORY_DEFAULT)
        registerReceiver(broadcastReceiver, filter)
    }


    private fun displayScanResult(initiatingIntent: Intent) {
        var decodedSource = initiatingIntent.getStringExtra(resources.getString(R.string.datawedge_intent_key_source))
        var decodedData = initiatingIntent.getStringExtra(resources.getString(R.string.datawedge_intent_key_data))
        var decodedLabelType = initiatingIntent.getStringExtra(resources.getString(R.string.datawedge_intent_key_label_type))
        if (null == decodedSource) {
            decodedSource = initiatingIntent.getStringExtra(resources.getString(R.string.datawedge_intent_key_source_legacy))
            decodedData = initiatingIntent.getStringExtra(resources.getString(R.string.datawedge_intent_key_data_legacy))
            decodedLabelType = initiatingIntent.getStringExtra(resources.getString(R.string.datawedge_intent_key_label_type_legacy))
        }
        Toast.makeText(this, "$decodedSource $decodedData $decodedLabelType", Toast.LENGTH_LONG).show()
    }
}