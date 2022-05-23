package com.example.pertemuan11_71190486

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtNama = findViewById<EditText>(R.id.edtNama)
        val edtNim = findViewById<EditText>(R.id.edtNIM)
        val edtIpk = findViewById<EditText>(R.id.edtIPK)
        val rg1 = findViewById<RadioGroup>(R.id.rg1)
        val rg2 = findViewById<RadioGroup>(R.id.rg2)
        val radioAsc = findViewById<RadioButton>(R.id.radioAsc)
        val radioDesc = findViewById<RadioButton>(R.id.radioDesc)
        val radioNim = findViewById<RadioButton>(R.id.radioNim)
        val radioNama = findViewById<RadioButton>(R.id.radioNama)
        val radioIpk = findViewById<RadioButton>(R.id.radioIpk)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnCari = findViewById<Button>(R.id.btnCari)
        val btnCariData = findViewById<Button>(R.id.btnCariData)
        val txtHasil = findViewById<TextView>(R.id.tvHasil)

        btnSimpan.setOnClickListener {
            val mahasiswa = Mahasiswa(
                edtNama.text.toString(),
                edtNim.text.toString().toInt(),
                edtIpk.text.toString().toInt()
            )
            db.collection("mahasiswa").add(mahasiswa)

            edtNama.setText("")
            edtNim.setText("")
            edtIpk.setText("")
            refreshData()
        }
        refreshData()

        btnCari.setOnClickListener {
            if (radioAsc.isChecked()) {
                if (radioNama.isChecked()) {
                    db.collection("mahasiswa")?.orderBy("nama", Query.Direction.ASCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for (doc in docs) {
                                output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                            }
                            txtHasil.setText(output)
                        }
                } else if (radioNim.isChecked()) {
                    db.collection("mahasiswa")?.orderBy("nim", Query.Direction.ASCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for (doc in docs) {
                                output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                            }
                            txtHasil.setText(output)
                        }
                } else if (radioIpk.isChecked()) {
                    db.collection("mahasiswa")?.orderBy("ipk", Query.Direction.ASCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for (doc in docs) {
                                output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                            }
                            txtHasil.setText(output)
                        }
                }
            } else if (radioDesc.isChecked()) {
                if (radioNama.isChecked()) {
                    db.collection("mahasiswa")?.orderBy("nama", Query.Direction.DESCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for (doc in docs) {
                                output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                            }
                            txtHasil.setText(output)

                        }
                } else if (radioNim.isChecked()) {
                    db.collection("mahasiswa")?.orderBy("nim", Query.Direction.DESCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for (doc in docs) {
                                output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                            }
                            txtHasil.setText(output)
                        }
                } else if (radioIpk.isChecked()) {
                    db.collection("mahasiswa")?.orderBy("ipk", Query.Direction.DESCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for (doc in docs) {
                                output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                            }
                            txtHasil.setText(output)
                        }
                }
            }
            rg1.clearCheck()
            rg2.clearCheck()
        }

        btnCariData.setOnClickListener {
            if (edtNama.text.toString() != "" && edtNama.text.toString() != null) {
                db.collection("mahasiswa")?.whereEqualTo("nama", edtNama.text.toString())?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for (doc in docs) {
                            output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                        }
                        txtHasil.setText(output)

                    }
            } else if (edtNim.text.toString() != "" && edtNim.text.toString() != null) {
                db.collection("mahasiswa")?.whereEqualTo("nim", edtNim.text.toString().toInt())?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for (doc in docs) {
                            output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                        }
                        txtHasil.setText(output)
                    }
            } else if (edtIpk.text.toString() != "" && edtNama.text.toString() != null) {
                db.collection("mahasiswa")?.whereEqualTo("ipk", edtIpk.text.toString().toInt())?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for (doc in docs) {
                            output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                        }
                        txtHasil.setText(output)
                    }
            }

        }
    }

    fun refreshData() {
        db.collection("mahasiswa")
            .get()
            .addOnSuccessListener {snapshot ->
                val txtData = findViewById<TextView>(R.id.tvData)
                var data = ""
                for(doc in snapshot){
                    data += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                }
                txtData.text = data
            }
    }
}

