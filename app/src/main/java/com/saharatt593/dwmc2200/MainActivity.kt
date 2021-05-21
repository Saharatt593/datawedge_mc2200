package com.saharatt593.dwmc2200

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
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
            startActivity(intent)
        }
    }
}