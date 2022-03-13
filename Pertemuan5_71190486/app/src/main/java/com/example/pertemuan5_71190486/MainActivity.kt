package com.example.pertemuan5_71190486

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = intent.getStringExtra("user")
        val tvName = findViewById<TextView>(R.id.tvName)
        tvName.text = "${user}"
    }
}