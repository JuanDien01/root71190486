package com.example.pertemuan5_71190486

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val edtUsername = findViewById<EditText>(R.id.edtUsername)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            login(edtUsername.text.toString(), edtPassword.text.toString())
        }
    }

    fun login(username: String, password: String) {
        if (password.equals("1234")){
            val i: Intent = Intent(this, MainActivity::class.java)
            i.putExtra("user", username)
            startActivity(i)
        }else{
            Toast.makeText(this, "Password Salah", Toast.LENGTH_LONG).show()
        }
    }
}