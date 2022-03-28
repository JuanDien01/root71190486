package com.example.pertemuan7_71190486

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContactDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_contact)

        val name = intent.getStringExtra("name")
        val nameview = findViewById<TextView>(R.id.edtPerson)
        nameview.setText(name).toString()

        val nohp = intent.getStringExtra("noHp")
        val nohpview = findViewById<TextView>(R.id.tvNoHp)
        nohpview.setText(nohp).toString()

        val email = intent.getStringExtra("email")
        val emailview = findViewById<TextView>(R.id.tvEmail)
        emailview.setText(email).toString()
    }
}