package com.saharatt593.dwmc2200

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val data_key = "data_id"
    }
    private lateinit var titleRoom:String

   private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
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
        onView()
        setConfigBroadcastReceiver()
        onEvent()
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

    private fun onView(){
        titleRoom = resources.getString(R.string.text_title_detail)+intent.extras?.getString(MainActivity.room_key)
        title = titleRoom
    }

    private fun onEvent(){
        btn_scan.setOnClickListener {
            val i = Intent()
            i.action =resources.getString(R.string.datawedge_intent_action)
            i.putExtra(resources.getString(R.string.datawedge_intent_key_scan_trigger), resources.getString(R.string.datawedge_start_scanning))
            sendBroadcast(i)
        }

        btn_next.setOnClickListener {
            val intent = Intent(
                this,
                AddActivity::class.java
            )
            intent.putExtra(MainActivity.room_key,titleRoom)
            intent.putExtra(data_key,edit_detail.text.toString())
            startActivity(intent)
        }
    }

    private fun setConfigBroadcastReceiver(){
        val filter = IntentFilter()
        filter.addAction(resources.getString(R.string.datawedge_intent_broadcast_action))
        filter.addCategory(Intent.CATEGORY_DEFAULT)
        registerReceiver(broadcastReceiver, filter)
    }


    private fun displayScanResult(initiatingIntent: Intent) {
        val decodedSource = initiatingIntent.getStringExtra(resources.getString(R.string.datawedge_intent_key_source))
        var decodedData = initiatingIntent.getStringExtra(resources.getString(R.string.datawedge_intent_key_data))
//        var decodedLabelType = initiatingIntent.getStringExtra(resources.getString(R.string.datawedge_intent_key_label_type))
        if (null == decodedSource) {
//            decodedSource = initiatingIntent.getStringExtra(resources.getString(R.string.datawedge_intent_key_source_legacy))
            decodedData = initiatingIntent.getStringExtra(resources.getString(R.string.datawedge_intent_key_data_legacy))
//            decodedLabelType = initiatingIntent.getStringExtra(resources.getString(R.string.datawedge_intent_key_label_type_legacy))
        }
        val dataText = edit_detail.text.toString()+"\n >"+decodedData
        edit_detail.setText(dataText)
//        Toast.makeText(this, "$decodedSource $decodedData $decodedLabelType", Toast.LENGTH_LONG).show()
    }
}