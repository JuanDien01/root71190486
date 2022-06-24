package com.example.a71190486_final

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class CreateActivity: AppCompatActivity() {
    var firestore : FirebaseFirestore? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val edtJudul = findViewById<EditText>(R.id.edtJudul)
        val edtGenre = findViewById<EditText>(R.id.edtGenre)
        val edtProduser = findViewById<EditText>(R.id.edtProduser)
        val edtPemeran = findViewById<EditText>(R.id.edtPemeran)
        val edtTahun = findViewById<EditText>(R.id.edtTahun)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val film = Film(
                edtJudul.text.toString(), edtGenre.text.toString(),
                edtProduser.text.toString(), edtPemeran.text.toString(),
                edtTahun.text.toString()
            )

            edtJudul.setText("")
            edtGenre.setText("")
            edtProduser.setText("")
            edtPemeran.setText("")
            edtTahun.setText("")
            firestore?.collection("film")?.add(film)?.addOnSuccessListener {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                Toast.makeText(this, "Create Data Berhasil", Toast.LENGTH_SHORT).show()
            }

        }
    }
}