package com.example.a71190486_final

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView

    var firestore: FirebaseFirestore? = null
    var listFilm = arrayListOf<Film>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firestore = FirebaseFirestore.getInstance()

        auth = FirebaseAuth.getInstance()

        //Variable untuk pencarian, menambah data, menampilkan data dengan recycler view
        val btnCari = findViewById<ImageButton>(R.id.btnCari)
        val edtCari = findViewById<EditText>(R.id.edtSearch)
        val rvFilm = findViewById<RecyclerView>(R.id.rvFilm)
        val btnAdd = findViewById<FloatingActionButton>(R.id.fab_add)

        //Fungsi btnAdd Data Film
        btnAdd.setOnClickListener {
            val i = Intent(this, CreateActivity::class.java)
            startActivity(i)
        }

        setSupportActionBar(findViewById(R.id.menu))
        supportActionBar?.setDisplayShowTitleEnabled(true)

        firestore?.collection("film")?.get()?.addOnSuccessListener { docs ->
            var hasil = ""
            for (doc in docs) {
                hasil += "${doc["judul"]}"
                val filmAdd = Film(
                    "${doc["judul"]}",
                    "${doc["genre"]}",
                    "${doc["produser"]}",
                    "${doc["pemeran"]}",
                    "${doc["tahun"]}"
                )
                listFilm.add(filmAdd)
            }
        }
        Handler().postDelayed({
            rvFilm.layoutManager = LinearLayoutManager(this)
            val adapter = FilmAdapter(listFilm, this)
            rvFilm.adapter = adapter
        }, 1000)

        //Fungsi btnCari Data Film
        btnCari.setOnClickListener {
            var pencarian = edtCari.text.toString()
            if (pencarian.isEmpty()) {
                Toast.makeText(this, "Pencarian Kosong", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    rvFilm.layoutManager = LinearLayoutManager(this)
                    val adapter = FilmAdapter(listFilm, this)
                    rvFilm.adapter = adapter
                }, 1000)
            } else if (!pencarian.isEmpty()) {
                listFilm.clear()
                firestore?.collection("film")?.get()?.addOnSuccessListener { docs ->
                    for (cari in docs) {
                        var bool = true
                        val filmCari = Film(
                            "${cari["judul"]}",
                            "${cari["genre"]}",
                            "${cari["produser"]}",
                            "${cari["pemeran"]}",
                            "${cari["tahun"]}"
                        )
                        if (pencarian.equals("${cari["judul"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listFilm.add(filmCari)
                        }
                        if (pencarian.equals("${cari["genre"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listFilm.add(filmCari)
                        }
                        if (pencarian.equals("${cari["produser"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listFilm.add(filmCari)
                        }
                        if (pencarian.equals("${cari["pemeran"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listFilm.add(filmCari)
                        }
                        if (pencarian.equals("${cari["tahun"]}") && bool) {
                            Toast.makeText(this, "Pencarian Ditemukan", Toast.LENGTH_SHORT).show()
                            bool = false
                            listFilm.add(filmCari)
                        }
                    }
                }?.addOnFailureListener {
                    Log.d("Gagal", "Data tidak ditemukan")
                }

                Handler().postDelayed({
                    rvFilm.layoutManager = LinearLayoutManager(this)
                    val adapter = FilmAdapter(listFilm, this)
                    rvFilm.adapter = adapter
                },1000)
            }

        }

        //Variable navigation bar
        val nav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        nav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.ic_home -> {
                    val home = Intent(this, MainActivity::class.java)
                    startActivity(home)
                }
                R.id.ic_profile -> {
                    val profile = Intent(this, ProfileActivity::class.java)
                    startActivity(profile)
                }
            }
            true
        }


    }



}