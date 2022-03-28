package com.example.pertemuan7_71190486

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listContact = arrayListOf<Contact>()
        listContact.add(Contact("Juu", R.mipmap.male1, "0812406211", "rahuloscar3@gmail.com"))
        listContact.add(Contact("Bastian", R.mipmap.male2, "0812346129", "rahuloscar13@gmail.com"))
        listContact.add(Contact("Tian", R.mipmap.male3, "0812361821", "rahuloscar4@gmail.com"))


        val rvContact = findViewById<RecyclerView>(R.id.rvContact)
        rvContact.layoutManager = LinearLayoutManager(this)
        val contactAdapter = ContactAdapter(listContact)
        rvContact.adapter = contactAdapter
    }
}