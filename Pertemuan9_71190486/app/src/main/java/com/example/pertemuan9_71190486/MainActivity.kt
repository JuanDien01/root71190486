package com.example.pertemuan9_71190486

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var sp: SharedPreferences? = null
    var spEdit: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sp = getSharedPreferences("mySP", MODE_PRIVATE)
        spEdit = sp?.edit()

        if (sp?.getBoolean("isLogin", false) == true) {
            setContentView(R.layout.activity_home)
            val btnLogout = findViewById<Button>(R.id.btnLogout)
            btnLogout.setOnClickListener{
                logout()
            }

            val bahasa = resources.getStringArray(R.array.bahasa)
            val spnBahasa = findViewById<Spinner>(R.id.spnBahasa)
            if(spnBahasa != null){
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, bahasa)
                spnBahasa.adapter = adapter
                spnBahasa.setSelection(sp!!.getInt("bahasa", 0))
            }
            spnBahasa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    spEdit?.putInt("bahasa", position)
                    spEdit?.commit()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }

            val ukuranFont = findViewById<EditText>(R.id.edtFont)
            ukuranFont.setText(sp?.getString("autoSave", ""))
            ukuranFont.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(font: CharSequence, start: Int, before: Int, count: Int) {}
                override fun beforeTextChanged(
                    font: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun afterTextChanged(font: Editable) {
                    spEdit?.putString("autoSave", font.toString())?.commit()
                }
            })



        } else {
            setContentView(R.layout.activity_main)
            val edtUsername = findViewById<EditText>(R.id.edtUsername)
            val edtPassword = findViewById<EditText>(R.id.edtPassword)
            val btnLogin = findViewById<Button>(R.id.btnLogin)
            btnLogin.setOnClickListener{
                login(edtUsername.text.toString(), edtPassword.text.toString())
            }
        }
    }

    fun login(username: String, password: String) {
        if (username.equals("admin") && password.equals("1234")) {
            spEdit?.putBoolean("isLogin", true)?.commit()
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        } else {
            Toast.makeText(this, "Username atau Password salah", Toast.LENGTH_LONG).show()
        }
    }

    fun logout(){
        spEdit?.putBoolean("isLogin", false)?.commit()
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }
}