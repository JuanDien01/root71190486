package com.example.pertemuan6_71190486

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class BFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_b, container, false)
        val btnFragmentB = v.findViewById<Button>(R.id.btnFragB)
        btnFragmentB.setOnClickListener {
            val i = Intent(context, ThirdActivity::class.java)
            context?.startActivity(i)
        }
        return v
    }
}