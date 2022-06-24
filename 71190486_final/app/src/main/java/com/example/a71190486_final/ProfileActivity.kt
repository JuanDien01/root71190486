package com.example.a71190486_final

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.a71190486_final.LoginActivity.Companion.EXTRA_NAME
import com.example.a71190486_final.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if (user != null){
            binding.tvEmail.setText(user.email)
        }
        binding.btnLogout.setOnClickListener {
            btnLogout()
        }
    }
    private fun btnLogout(){
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        val Logout = Intent(this, LoginActivity::class.java)
        startActivity(Logout)
    }
}