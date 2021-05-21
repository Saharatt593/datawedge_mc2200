package com.saharatt593.dwmc2200

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        onView()
        onEvent()
    }

    private fun onView(){
        text_room_title.text = intent.extras?.getString(MainActivity.room_key)
        val dataDetail =resources.getString(R.string.text_room_add_detail)+intent.extras?.getString(DetailActivity.data_key)
        text_detail.text = dataDetail
    }

    private fun onEvent() {
        btn_next.setOnClickListener {
            val intent = Intent(
                this,
                MainActivity::class.java
            )
            startActivity(intent)
            finishAffinity()
        }
    }
}