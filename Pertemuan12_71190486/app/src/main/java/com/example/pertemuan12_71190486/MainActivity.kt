package com.example.pertemuan12_71190486

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtKota = findViewById<EditText>(R.id.edtKota)
        val btnCek = findViewById<Button>(R.id.btnCek)

        btnCek.setOnClickListener {
            cekCuaca(edtKota.text.toString())
        }
    }
    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    fun cekCuaca(kota: String){
        val url = "https://api.openweathermap.org/data/2.5/forecast?q=${kota}&appid=0e49df77fd7a55d65d5a3e3135290e2d&units=metric"
        val queue = Volley.newRequestQueue(this)
        val txvHasil = findViewById<TextView>(R.id.txvHasil)
        val request = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val weatherjson = JSONObject(response).getJSONArray("list")
                var str = ""
                val cuacasekarang = weatherjson.getJSONObject(1).getJSONArray("weather").getJSONObject(0).getString("description")
                val cuacabesok = weatherjson.getJSONObject(9).getJSONArray("weather").getJSONObject(0).getString("description")
                val cuacalusa = weatherjson.getJSONObject(17).getJSONArray("weather").getJSONObject(0).getString("description")
                str += "Cuaca di $kota Sekarang : $cuacasekarang\nCuaca di $kota Besok : $cuacabesok\nCuaca di $kota Lusa : $cuacalusa"
                txvHasil.text = str
            },
            {
                txvHasil.text = "Tidak Ada Nama Kota $kota"
            }
        )
        queue.add(request)
    }
}