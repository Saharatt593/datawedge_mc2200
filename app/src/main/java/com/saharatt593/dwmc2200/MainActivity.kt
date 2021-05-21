package com.saharatt593.dwmc2200

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val room_key = "room_id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        event()
    }

    private fun event() {
        btn_next.setOnClickListener {
            val intent = Intent(
                this,
                DetailActivity::class.java
            )
            intent.putExtra(room_key,edit_room.text.toString())
            startActivity(intent)
        }
    }
}