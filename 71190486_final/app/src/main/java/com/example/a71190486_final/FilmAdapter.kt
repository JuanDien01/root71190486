package com.example.a71190486_final

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class FilmAdapter(private val filmList: ArrayList<Film>, var context: Context):RecyclerView.Adapter<FilmAdapter.FilmHolder>() {
    inner class FilmHolder(val view: View): RecyclerView.ViewHolder(view) {


        var firestore: FirebaseFirestore? = null
        fun bind(film: Film, context: Context) {

            firestore = FirebaseFirestore.getInstance()

            val judul = view.findViewById<TextView>(R.id.tvJudul)
            val genre = view.findViewById<TextView>(R.id.tvGenre)
            val produser = view.findViewById<TextView>(R.id.tvProduser)
            val pemeran = view.findViewById<TextView>(R.id.tvPemeran)
            val tahun = view.findViewById<TextView>(R.id.tvTahun)

            judul.setText(film.judul)
            genre.setText(film.genre)
            produser.setText(film.produser)
            pemeran.setText(film.pemeran)
            tahun.setText(film.tahun)


            val btnEdit = view.findViewById<Button>(R.id.btnEdit)
            val btnHapus = view.findViewById<Button>(R.id.btnHapus)


            btnEdit.setOnClickListener {
                val intent = Intent(context, UpdateActivity::class.java)
                intent.putExtra("judul", judul.text)
                intent.putExtra("genre", genre.text)
                intent.putExtra("produser", produser.text)
                intent.putExtra("pemeran", pemeran.text)
                intent.putExtra("tahun", tahun.text)
                context.startActivity(intent)
            }
            btnHapus.setOnClickListener {

                firestore?.collection("film")?.whereEqualTo("judul",judul.text)?.get()!!.addOnSuccessListener {
                    for (hapus in it){
                        firestore?.collection("film")?.document(hapus.id)?.delete()
                        Toast.makeText(context, "Delete Data Berhasil", Toast.LENGTH_SHORT).show()
                    }

                }
                filmList.removeAt(adapterPosition)
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_film, parent, false)
        return FilmHolder(v)
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        holder.bind(filmList[position],context)
    }

    override fun getItemCount(): Int {
        return filmList.size
    }
}