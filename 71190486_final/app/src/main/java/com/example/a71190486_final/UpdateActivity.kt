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

class UpdateActivity : AppCompatActivity() {

    var firestore: FirebaseFirestore? = null
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val getJudul = intent.getStringExtra("judul").toString()
        val getGenre = intent.getStringExtra("genre").toString()
        val getProduser = intent.getStringExtra("produser").toString()
        val getPemeran = intent.getStringExtra("pemeran").toString()
        val getTahun = intent.getStringExtra("tahun").toString()

        val edtJudul = findViewById<EditText>(R.id.edtUpdateJudul)
        val edtGenre = findViewById<EditText>(R.id.edtUpdateGenre)
        val edtProduser = findViewById<EditText>(R.id.edtUpdateProduser)
        val edtPemeran= findViewById<EditText>(R.id.edtUpdatePemeran)
        val edtTahun = findViewById<EditText>(R.id.edtUpdateTahun)

        val btnUpdate = findViewById<Button>(R.id.btnUpdate)

        edtJudul.setText(getJudul)
        edtGenre.setText(getGenre)
        edtProduser.setText(getProduser)
        edtPemeran.setText(getPemeran)
        edtTahun.setText(getTahun)

        btnUpdate.setOnClickListener {
            val updateFilm = Film(
                edtJudul.text.toString(),
                edtGenre.text.toString(),
                edtProduser.text.toString(),
                edtPemeran.text.toString(),
                edtTahun.text.toString()
            )
            firestore?.collection("film")?.whereEqualTo("judul", getJudul)?.get()!!
                .addOnSuccessListener {
                    for (update in it) {
                        firestore?.collection("film")?.document(update.id)?.set(updateFilm)
                            ?.addOnCompleteListener {
                                if (it.isSuccessful) {
                                    Toast.makeText(this, "Update Data Berhasil", Toast.LENGTH_SHORT)
                                        .show()
                                    val i = Intent(this, MainActivity::class.java)
                                    startActivity(i)
                                }
                            }
                    }
                }
        }
    }
}